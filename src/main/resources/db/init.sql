CREATE TABLE Companies (
                           company_id UUID PRIMARY KEY,
                           name VARCHAR(255),
                           address TEXT,
                           phone VARCHAR(20),
                           email VARCHAR(255)
);

CREATE TABLE Users (
                       user_id UUID PRIMARY KEY,
                       username VARCHAR(255),
                       password VARCHAR(255),
                       email VARCHAR(255),
                       company_id UUID,
                       FOREIGN KEY (company_id) REFERENCES Companies(company_id)
);

CREATE TABLE Categories (
                            category_id UUID PRIMARY KEY,
                            company_id UUID,
                            name VARCHAR(255),
                            description TEXT,
                            FOREIGN KEY (company_id) REFERENCES Companies(company_id)
);

CREATE TABLE Transactions (
                              transaction_id UUID PRIMARY KEY,
                              company_id UUID,
                              user_id UUID,
                              amount DECIMAL(10, 2),
                              transaction_type VARCHAR(10),
                              timestamp TIMESTAMP,
                              category_id UUID,
                              description TEXT,
                              receipt_url VARCHAR(255),
                              FOREIGN KEY (company_id) REFERENCES Companies(company_id),
                              FOREIGN KEY (user_id) REFERENCES Users(user_id),
                              FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);
