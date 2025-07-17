# Installation

## Requierements

- MySQL Localy or docker
- Java 17 +
- Maven installed
- Git installed

---

## Git

Create a folder, where you want to have this project.

```bash
cd path/to/wanted/folder

mkdir folder
```

Clone the git Repo

```bash
git clone https://github.com/wettsteinremodev/StorageUnitSystem.git
```

## Database

Starting the Docker-container:

```bash
cd path/to/folder/StprageUnitSystem/storageUnitSytemService/DB

docker compose up -d
```

Check if the container is running:

```bash
docker ps -a
```

In you **_workbench_** you can run the Scripts or paste the code.

you can find all the querrys in this folder:

```bash
cd path/to/folder/StprageUnitSystem/MySQL_Querrys
```

1. run the StorageUnit.sql script
2. run the AdminUser.sql script

The querry for creating the Database: `StorageUnit.sql`

```sql
-- Create database storage_mangagement
DROP DATABASE IF EXISTS storage_management;
CREATE DATABASE IF NOT EXISTS storage_management;
USE storage_management;

```

The querry for creating the User: `AdminUser.sql`

```sql
-- Create the user adminStUn (if not exists)
CREATE USER IF NOT EXISTS 'adminStUn'@'%' IDENTIFIED BY 'admin1234';

-- Grant all!!! privileges on the database
GRANT ALL PRIVILEGES ON storage_management.* TO 'adminStUn'@'%';
GRANT GRANT OPTION ON storage_management.* TO 'adminStUn'@'%';

-- Apply the changes
FLUSH PRIVILEGES;

-- See if user Is Created
SELECT User, Host FROM mysql.user WHERE User = 'adminStUn';
-- Show User Grants
SHOW GRANTS FOR 'adminStUn'@'%';

```

---

## Spring-Setup

Open the folder `storageUnitSytemService` in you favourite IDE.

you need to be in that folde or you cant run the commands from the terminal.

```bash
cd path/to/folder/StorageUnitSystem/storageUnitSystemService
```

Maven install if needed

```bash
mvn install
```

Maven Testing (Running the tests)

```bash
mvn test
```

Starting the app

1. Running from your editor
2. Running thorugh the terminal

```bash
   mvn spring-boot:run
```

## Dummy-data

The querry for creating the Dummy-Data: `DummyData.sql`

```sql
USE storage_management;

-- Clear existing data
DELETE FROM rented;
DELETE FROM storage_unit;
DELETE FROM app_user;

-- Insert Users
INSERT INTO app_user (id, user_name, email, password) VALUES
(1, 'Alice', 'alice@example.com', 'password123'),
(2, 'Bob', 'bob@example.com', 'secret456'),
(3, 'Charlie', 'charlie@example.com', 'charliePass'),
(4, 'Diana', 'diana@example.com', 'dianaSecure'),
(5, 'Ethan', 'ethan@example.com', 'ethanPass1'),
(6, 'Fiona', 'fiona@example.com', 'fionaPass1'),
(7, 'George', 'george@example.com', 'georgePass1'),
(8, 'Hannah', 'hannah@example.com', 'hannahPass1'),
(9, 'Ivan', 'ivan@example.com', 'ivanPass1'),
(10, 'Julia', 'julia@example.com', 'juliaPass1');

-- Insert Storage Units
INSERT INTO storage_unit (id, name, size_in_m2, price_per_month, is_available) VALUES
(1, 'Unit A', 10.0, 100.00, TRUE),
(2, 'Unit B', 20.0, 150.00, TRUE),
(3, 'Unit C', 15.5, 120.00, TRUE),
(4, 'Unit D', 25.0, 180.00, TRUE),
(5, 'Unit E', 30.0, 200.00, TRUE),
(6, 'Unit F', 50.0, 300.00, TRUE),
(7, 'Unit G', 5.0, 80.00, TRUE),
(8, 'Unit H', 60.0, 350.00, TRUE),
(9, 'Unit I', 45.0, 290.00, TRUE),
(10, 'Unit J', 12.0, 110.00, TRUE);

-- Insert Rentals
INSERT INTO rented (id, user_id, storage_unit_id, start_date, end_date) VALUES
(1, 1, 1, '2023-05-01', '2024-05-01'),
(2, 1, 2, '2024-06-01', '2025-06-01'),
(3, 2, 3, '2024-01-01', '2025-01-01'),
(4, 2, 4, '2025-02-01', '2026-02-01'),
(5, 3, 5, '2024-07-15', '2025-07-15'),
(6, 3, 6, '2025-08-01', '2026-08-01'),
(7, 4, 7, '2023-09-10', '2024-09-10'),
(8, 5, 8, '2024-03-01', '2025-03-01'),
(9, 5, 9, '2025-04-01', '2026-04-01'),
(10, 6, 10, '2024-01-20', '2025-01-20'),
(11, 7, 1, '2023-11-05', '2024-11-05'),
(12, 7, 2, '2024-12-05', '2025-12-05'),
(13, 8, 3, '2024-06-10', '2025-06-10'),
(14, 9, 4, '2025-01-01', '2026-01-01'),
(15, 9, 5, '2026-01-02', '2027-01-02'),
(16, 10, 6, '2024-04-01', '2025-04-01'),
(17, 10, 7, '2025-05-01', '2026-05-01'),
(18, 10, 8, '2026-06-01', '2027-06-01');

```

## End
