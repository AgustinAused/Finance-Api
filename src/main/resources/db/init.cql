CREATE KEYSPACE IF NOT EXISTS finance_keyspace
    WITH replication = {
        'class': 'SimpleStrategy',
        'replication_factor': 1
    };

USE finance_keyspace;