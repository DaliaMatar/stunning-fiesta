import pymysql.cursors

# Connect to the database
connection = pymysql.connect(host='localhost',
                             user='root',
                             password='pwd',
                             db='base',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor,
                             unix_socket="/tmp/mysql.sock")

try:
    with connection.cursor() as cursor:
        # Create a new record
        sql = "INSERT INTO `users` (`email`, `password`) VALUES (%s, %s)"
        cursor.execute(sql, ('webmaster@python.org', 'very-secret'))

    # connection is not autocommit by default. So you must commit to save
    # your changes.
    connection.commit()

    with connection.cursor() as cursor:
        # Read a single record
        sql = "SELECT `id`, `password` FROM `users` WHERE `email`=%s"
        cursor.execute(sql, ('webmaster@python.org',))
        result = cursor.fetchone()
        print(result)
finally:
    connection.close()

# CREATE TABLE `users` (
#     `id` int(11) NOT NULL AUTO_INCREMENT,
#     `email` varchar(255) COLLATE utf8_bin NOT NULL,
#     `password` varchar(255) COLLATE utf8_bin NOT NULL,
#     PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
# AUTO_INCREMENT=1 ;
