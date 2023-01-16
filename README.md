<p align="center">
      <img src="https://www.logomaker.com/api/main/images/1j+ojFVDOMkX9Wytexe43D6kh...SGpBFNkB3OwXs1M3EMoAJtlicujvpv8PU6" alt="Project Logo" width="30%">
</p>

## Описание
OfficeRent - это веб-сервис, использующийся для обработки заявок поступающих от арендаторов офисного центра.

## Использованные технологии
Java, Maven, Spring Framework, MySQL

## Декомпозиция проекта
1. Личный кабинет
   * Страница пользователя
   * Страница администратора
2. Системные роли
   * Сохранение ролей в базе данных
   * Возможность изменения роли пользователя администратором
3. Хранение данных об офисе
   * Хранение в базе данных названия, описания, адреса и цены офиса
   * Хранение в базе данных арендованных офисов

## Запуск
```mysql
Создайте нового пользователя и пустую БД:
mysql -u root -p -e 'CREATE DATABASE IF NOT EXISTS officerent;'
mysql -u root -p -e "CREATE USER 'officerent'@'localhost' IDENTIFIED BY 'officerent12345';"
mysql -u root -p -e "GRANT ALL ON officerent.* TO 'officerent'@'localhost';"
```
```
mvn compile
mvn spring-boot:run
```

## Скриншоты

<table>
    <tr>
        <td>
            <img src="">
        </td>
        <td>
            <img src="">
        </td>
    </tr>
</table>

## Автор
Студент группы ИКПИ-01 Миронов Кирилл
