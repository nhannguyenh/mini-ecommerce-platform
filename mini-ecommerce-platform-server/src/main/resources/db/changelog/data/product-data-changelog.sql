-- liquibase formatted sql

-- changeset Nhan Nguyen:MECP-11-1
INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -1,
    'MacBook Neo',
    'Apple 2026 MacBook Neo 13-inch Laptop with A18 Pro chip: Built for AI and Apple Intelligence, Liquid Retina Display, 8GB Unified Memory, 256GB SSD Storage, 1080p FaceTime HD Camera',
    599,
    'PRD1',
    100,
    '/products/macbook_neo.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -2,
    'MacBook Air',
    'Apple 2026 MacBook Air 15-inch Laptop with M5 chip: Built for AI, 15.3-inch Liquid Retina Display, 16GB Unified Memory, 512GB SSD, 12MP Center Stage Camera, Touch ID, Wi-Fi 7; Sky Blue',
    1099,
    'PRD2',
    100,
    '/products/macbook_air.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -3,
    'MacBook Pro',
    'Apple 2026 MacBook Pro Laptop with Apple M5 Pro chip with 18-core CPU and 20-core GPU',
    1599,
    'PRD3',
    100,
    '/products/macbook_pro.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -4,
    'Apple Watch Ultra 3',
    'Apple Watch Ultra 3 [GPS + Cellular 49mm] Running & Multisport Smartwatch',
    873,
    'PRD4',
    100,
    '/products/apple_watch_ultra_3.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -5,
    'Ipad Pro',
    'Apple iPad Pro 13-inch (M5): Ultra Retina XDR Display, 256GB, Landscape 12MP Front Camera',
    1199,
    'PRD5',
    100,
    '/products/ipad_pro.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -6,
    'Ipad Air',
    'Apple iPad Air 13-inch (M4): Liquid Retina Display, 128GB, 12MP Front',
    749,
    'PRD6',
    100,
    '/products/ipad_air.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -7,
    'Ipad Mini',
    'Apple iPad mini (A17 Pro): Apple Intelligence, 8.3-inch Liquid Retina Display, 128GB, Wi-Fi 6E, 12MP Front',
    489,
    'PRD7',
    100,
    '/products/ipad_mini.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -8,
    'iMac',
    'Apple 2024 iMac All-in-One Desktop Computer with M4 chip with 8-core CPU and 8-core GPU',
    1249,
    'PRD8',
    100,
    '/products/imac.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -9,
    'Mac mini',
    'Apple 2024 Mac mini Desktop Computer with M4 chip with 10‑core CPU and 10‑core GPU',
    579,
    'PRD9',
    100,
    '/products/mac_mini.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO products(id, name, description, price, sku, stock_quantity, image_url, created_on, modified_on)
VALUES (
    -10,
    'AirPods Pro 3',
    'Apple AirPods Pro 3 Wireless Earbuds, Active Noise Cancellation, Live Translation, Heart Rate Sensing, Hearing Aid Feature, Bluetooth Headphones, Spatial Audio, High-Fidelity Sound, USB-C Charging',
    224,
    'PRD10',
    100,
    '/products/air_pods_pro_3.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
