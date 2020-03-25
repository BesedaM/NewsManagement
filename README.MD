<h2>NewsManagement project</h2>

Проект выполнен на Spring Framework

Согласно заданию проект разбит на 3 модуля:
1. repository-jdbc - DAO выполнен на JdbcTemplate;
2. service;
3. rest controller.

В каждом модуле свои тесты (за исключением модуля Controller), тесты собраны в TestSuite. 

Controller отдельно протестирован при помощи Postman, набор тестов представлен в файле News_management.postman_collection.json.

Работа с БД велась на PostgreSQL
