CREATE TABLE Companies (
                           company_id bigint PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           address TEXT,
                           phone VARCHAR(20) CHECK (phone ~ '^\+?[0-9]*$')
);


CREATE TABLE Users (
                    user_id bigint PRIMARY KEY,
                    username VARCHAR(255) UNIQUE NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    email VARCHAR(255) UNIQUE NOT NULL CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'),
                    first_name VARCHAR(255) NOT NULL,
                    last_name VARCHAR(255) NOT NULL,
                    active BOOLEAN DEFAULT FALSE,
                    company_id bigint,
                    FOREIGN KEY (company_id) REFERENCES Companies(company_id) ON DELETE SET NULL
);

CREATE TABLE Categories (
                            category_id bigint PRIMARY KEY,
                            company_id bigint,
                            name VARCHAR(255) NOT NULL,
                            description TEXT,
                            FOREIGN KEY (company_id) REFERENCES Companies(company_id) ON DELETE CASCADE
);

CREATE TABLE Transactions (
                              transaction_id bigint PRIMARY KEY,
                              company_id bigint,
                              user_id bigint,
                              amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0),
                              transaction_type VARCHAR(10) NOT NULL CHECK (transaction_type IN ('credit', 'debit')),
                              timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              category_id bigint,
                              description TEXT,
                              receipt_url VARCHAR(255) CHECK (receipt_url ~* '^(http|https)://'),
                              FOREIGN KEY (company_id) REFERENCES Companies(company_id) ON DELETE CASCADE,
                              FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE SET NULL,
                              FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE SET NULL
);

