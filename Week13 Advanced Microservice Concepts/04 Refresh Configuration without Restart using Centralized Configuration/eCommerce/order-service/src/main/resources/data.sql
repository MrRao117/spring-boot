INSERT INTO orders (total_price, order_status) VALUES
(100.50, 'PENDING'),
(200.75, 'CONFIRMED'),
(300.00, 'DELIVERED'),
(150.25, 'PENDING'),
(120.06, 'CANCELLED'),
(210.50, 'PENDING'),
(350.75, 'DELIVERED'),
(110.80, 'CONFIRMED'),
(188.20, 'PENDING'),
(258.48, 'CANCELLED');


-- Inserting order items, ensuring each order has items

INSERT INTO order_item (order_id, product_id, quantity) VALUES
(1, 101, 2),
(1, 102, 1),
(2, 183, 1),
(2, 184, 3),
(3, 185, 1),
(3, 106, 2),
(4, 187, 2),
(5, 188, 3),
(6, 189, 2),
(7, 116, 1),
(8, 111, 2),
(9, 112, 3),
(10, 113, 2);