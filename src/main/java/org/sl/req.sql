/*ошибки в расписании (фильмы накладываются друг на друга),
  отсортированные по возрастанию времени. Выводить надо колонки
  «фильм 1», «время начала», «длительность», «фильм 2»,
  «время начала», «длительность»;*/

SELECT m1.name       AS "movie 1",
       s1.start_time AS "time start",
       m1.duration   AS "duration",
       m2.name       AS "movie 2",
       s2.start_time AS "time start",
       m2.duration   AS "duration"
FROM Sessions s1
         JOIN Sessions s2 ON s1.start_time < s2.start_time
         JOIN Movies m1 ON s1.movie_id = m1.id
         JOIN Movies m2 ON s2.movie_id = m2.id
WHERE s1.start_time + INTERVAL '1' MINUTE * m1.duration > s2.start_time
ORDER BY s1.start_time ASC;

/*+----------------------+--------------------------+--------+---------------------------------------------+--------------------------+--------+
|movie 1               |time start                |duration|movie 2                                      |time start                |duration|
+----------------------+--------------------------+--------+---------------------------------------------+--------------------------+--------+
|The Godfather: Part II|2023-04-21 12:00:00.000000|120     |Schindler's List                             |2023-04-21 13:00:00.000000|120     |
|12 Angry Men          |2023-04-21 12:00:00.000000|90      |Schindler's List                             |2023-04-21 13:00:00.000000|120     |
|Schindler's List      |2023-04-21 13:00:00.000000|120     |The Lord of the Rings: The Return of the King|2023-04-21 14:00:00.000000|60      |
|Schindler's List      |2023-04-21 13:00:00.000000|120     |Pulp Fiction                                 |2023-04-21 14:00:00.000000|90      |
+----------------------+--------------------------+--------+---------------------------------------------+--------------------------+--------+
*/


/*список фильмов, для каждого — с указанием общего числа посетителей
  за все время, среднего числа зрителей за сеанс и общей суммы сборов
  по каждому фильму (отсортировать по убыванию прибыли).*/

SELECT m.name                   AS "Movie",
       SUM(tickets_sold)        AS "Total Visitors",
       AVG(viewers_per_session) AS "Avg Viewers per Session",
       SUM(revenue)             AS "Total Revenue"
FROM Movies m
         JOIN Sessions s ON s.movie_id = m.id
         JOIN (SELECT session_id,
                      COUNT(*)                                     AS tickets_sold,
                      COUNT(*) * AVG(price)                        AS revenue,
                      AVG(COUNT(*)) OVER (PARTITION BY session_id) AS viewers_per_session
               FROM Tickets
                        JOIN Sessions ON Tickets.session_id = Sessions.id
               GROUP BY session_id) t ON t.session_id = s.id
GROUP BY m.name
ORDER BY SUM(revenue) DESC;

/*+---------------------------------------------+--------------+-----------------------+-------------+
|Movie                                        |Total Visitors|Avg Viewers per Session|Total Revenue|
+---------------------------------------------+--------------+-----------------------+-------------+
|The Dark Knight                              |4             |2                      |1000         |
|The Shawshank Redemption                     |5             |2.5                    |900          |
|Schindler's List                             |3             |3                      |600          |
|The Godfather                                |4             |4                      |600          |
|The Godfather: Part II                       |1             |1                      |200          |
|12 Angry Men                                 |1             |1                      |150          |
|Pulp Fiction                                 |1             |1                      |150          |
|The Lord of the Rings: The Return of the King|1             |1                      |100          |
+---------------------------------------------+--------------+-----------------------+-------------+
*/


/*число посетителей и кассовые сборы, сгруппированные по времени
  начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00
  (сколько посетителей пришло с 9 до 15 часов и т.д.).*/

SELECT CASE
           WHEN EXTRACT(HOUR FROM s.start_time) BETWEEN 9 AND 14 THEN '9-14'
           WHEN EXTRACT(HOUR FROM s.start_time) BETWEEN 15 AND 17 THEN '15-17'
           WHEN EXTRACT(HOUR FROM s.start_time) BETWEEN 18 AND 20 THEN '18-20'
           ELSE '21-00'
           END      AS "Time Slot",
       COUNT(*)     AS "Visitors",
       SUM(s.price) AS "Ticket Sales"
FROM Sessions s
         JOIN Tickets t ON s.id = t.session_id
GROUP BY CASE
             WHEN EXTRACT(HOUR FROM s.start_time) BETWEEN 9 AND 14 THEN '9-14'
             WHEN EXTRACT(HOUR FROM s.start_time) BETWEEN 15 AND 17 THEN '15-17'
             WHEN EXTRACT(HOUR FROM s.start_time) BETWEEN 18 AND 20 THEN '18-20'
             ELSE '21-00'
             END
ORDER BY MIN(EXTRACT(HOUR FROM s.start_time));

/*+---------+--------+------------+
|Time Slot|Visitors|Ticket Sales|
+---------+--------+------------+
|9-14     |15      |2200        |
|15-17    |2       |600         |
|18-20    |3       |900         |
+---------+--------+------------+
*/


/*перерывы 30 минут и более между фильмами — выводить по
  уменьшению длительности перерыва. Колонки «фильм 1», «время начала»,
  «длительность», «время начала второго фильма», «длительность перерыва»;*/

SELECT m1.name                                                                                        as movie1,
       s1.start_time                                                                                  as start_time,
       m1.duration                                                                                    as duration,
       s2.start_time                                                                                  as start_time_movie2,
       EXTRACT(epoch FROM (s2.start_time - (s1.start_time + INTERVAL '1 minutes' * m1.duration)) / 60) as break_duration
FROM sessions s1
         JOIN movies m1 ON s1.movie_id = m1.id
         JOIN sessions s2 ON s2.start_time > (s1.start_time + INTERVAL '1 minutes' * m1.duration)
         JOIN movies m2 ON s2.movie_id = m2.id
WHERE NOT EXISTS(
        SELECT 1
        FROM sessions s3
        WHERE s3.start_time BETWEEN s1.start_time AND s2.start_time
          AND s3.id != s1.id
          AND s3.id != s2.id
    )
  AND s2.start_time - (s1.start_time + INTERVAL '1 minutes' * m1.duration) >= INTERVAL '30 minutes'
ORDER BY break_duration DESC;

/*+---------------+--------------------------+--------+--------------------------+--------------+
|movie1         |start_time                |duration|start_time_movie2         |break_duration|
+---------------+--------------------------+--------+--------------------------+--------------+
|The Dark Knight|2023-04-21 19:00:00.000000|60      |2023-04-21 22:00:00.000000|120           |
+---------------+--------------------------+--------+--------------------------+--------------+
*/