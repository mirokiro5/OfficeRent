INSERT INTO role(name) VALUES
                        ('ADMIN_ROLE'),
                        ('MANAGER_ROLE'),
                        ('USER_ROLE');
INSERT INTO office(address, description, image, name, price) VALUES
                        ('address1', 'description1', 'bigoffice.jpg', 'Большой офис', 120000),
                        ('address2', 'description2', 'smalloffice.jpg', 'Маленький офис', 70000),
                        ('address3', 'description3', 'office.jpg', 'Средний офис', 90000),
                        ('address4', 'description4', 'office.jpg', 'name4', 4),
                        ('address5', 'description5', 'office.jpg', 'name5', 5);
UPDATE users_roles SET roles_id = 1 WHERE user_id = 1;

SELECT * FROM office;