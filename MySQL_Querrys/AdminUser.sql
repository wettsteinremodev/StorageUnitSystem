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


