CREATE TABLE `person`
(
    `id`          BIGINT(19) NOT NULL AUTO_INCREMENT,
    `typeId`      BIGINT(19) NOT NULL DEFAULT '0',
    `firstname`   VARCHAR(50) NULL DEFAULT NULL COLLATE 'cp1250_general_ci',
    `lastname`    VARCHAR(50) NULL DEFAULT NULL COLLATE 'cp1250_general_ci',
    `email`       VARCHAR(50) NULL DEFAULT NULL COLLATE 'cp1250_general_ci',
    `phone`       VARCHAR(50) NULL DEFAULT NULL COLLATE 'cp1250_general_ci',
    `password`    VARCHAR(50) NULL DEFAULT NULL COLLATE 'cp1250_general_ci',
    `accessMode`  BIGINT(19) NULL DEFAULT NULL,
    `access_mode` INT(10) NULL DEFAULT NULL,
    `type_id`     INT(10) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) COLLATE='cp1250_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;
