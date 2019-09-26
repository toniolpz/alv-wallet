-- Create Wallet Database
CREATE DATABASE wallet;
USE wallet;

-- Create balance table
CREATE TABLE balance (
    id SMALLINT NOT NULL AUTO_INCREMENT,
    user_id SMALLINT NOT NULL,
    currency ENUM ('EUR', 'USD', 'GBP'),
    amount DECIMAL (18,8),
    PRIMARY KEY (id)
);
