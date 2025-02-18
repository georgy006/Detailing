ALTER TABLE users
ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES role(id);

ALTER TABLE car
ADD CONSTRAINT fk_car_user FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE orders
ADD CONSTRAINT fk_orders_car FOREIGN KEY (car_id) REFERENCES car(id),
ADD CONSTRAINT fk_orders_service FOREIGN KEY (service_id) REFERENCES service(id),
ADD CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id);