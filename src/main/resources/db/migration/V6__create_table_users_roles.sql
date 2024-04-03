CREATE TABLE users_roles(
`id` BIGINT NOT NULL AUTO_INCREMENT,
`id_users` BIGINT NOT NULL,
`id_roles` BIGINT NOT NULL,
PRIMARY KEY(`id`),
FOREIGN KEY(`id_users`) REFERENCES `users`(`id`),
FOREIGN KEY(`id_roles`) REFERENCES `roles`(`id`)
);