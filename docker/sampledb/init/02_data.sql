INSERT INTO companies
(
    id,
    name,
    location
)
VALUES
(
    1,
    '株式会社サンプル',
    '東京都国分寺市'
),
(
    2,
    '774.inc',
    '東京都新宿区'
);

SELECT SETVAL('companies_id_seq', 2);

INSERT INTO users
(
    username,
    full_name,
    company_id
)
VALUES
(
    'sample',
    '田中 太郎',
    1
),
(
    'haneru-inaba',
    'イナバハネル',
    2
),
(
    'patra-suoh',
    'スオウパトラ',
    2
),
(
    'mea-kagura',
    'カグラメア',
    NULL
);
