CREATE TABLE Companies (
                           id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           name VARCHAR(255) NOT NULL UNIQUE,
                           address TEXT,
                           email VARCHAR(255) CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'),
                           phone VARCHAR(20) CHECK (phone ~ '^\+?[0-9]*$')
    );

CREATE TABLE Users (
                       id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'),
                       firstName VARCHAR(255) NOT NULL,
                       lastName VARCHAR(255) NOT NULL,
                       active BOOLEAN DEFAULT FALSE,
                       phone VARCHAR(20) CHECK (phone ~ '^\+?[0-9]*$'),
                       avatarUrl VARCHAR(255) CHECK (avatarUrl ~* '^(http|https)://'),
                       company_id bigint,
                       FOREIGN KEY (company_id) REFERENCES Companies(id) ON DELETE SET NULL
);


CREATE TABLE Categories (
                            id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                            company_id bigint,
                            name VARCHAR(255) NOT NULL,
                            description TEXT,
                            FOREIGN KEY (company_id) REFERENCES Companies(id) ON DELETE CASCADE
);

CREATE TABLE Transactions (
                              id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                              company_id bigint,
                              user_id bigint,
                              amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0),
                              type VARCHAR(10) NOT NULL CHECK (type IN ('income', 'expense')),
                              timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              category_id bigint,
                              description TEXT,
                              receipt_url VARCHAR(255) CHECK (receipt_url ~* '^(http|https)://'),
    FOREIGN KEY (company_id) REFERENCES Companies(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE SET NULL,
    FOREIGN KEY (category_id) REFERENCES Categories(id) ON DELETE SET NULL
);

