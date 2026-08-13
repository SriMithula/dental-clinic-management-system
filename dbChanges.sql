-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema SunriseDentalClinic
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SunriseDentalClinic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SunriseDentalClinic` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `SunriseDentalClinic` ;

-- -----------------------------------------------------
-- Table `SunriseDentalClinic`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SunriseDentalClinic`.`users` ;

CREATE TABLE IF NOT EXISTS `SunriseDentalClinic`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SunriseDentalClinic`.`patients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SunriseDentalClinic`.`patients` ;

CREATE TABLE IF NOT EXISTS `SunriseDentalClinic`.`patients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `contact_number` VARCHAR(45) NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SunriseDentalClinic`.`dentists`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SunriseDentalClinic`.`dentists` ;

CREATE TABLE IF NOT EXISTS `SunriseDentalClinic`.`dentists` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `contact_number` VARCHAR(255) NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SunriseDentalClinic`.`treatments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SunriseDentalClinic`.`treatments` ;

CREATE TABLE IF NOT EXISTS `SunriseDentalClinic`.`treatments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `cost` DOUBLE NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SunriseDentalClinic`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SunriseDentalClinic`.`appointments` ;

CREATE TABLE IF NOT EXISTS `SunriseDentalClinic`.`appointments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `appointment_no` VARCHAR(45) NOT NULL,
  `appointment_date` DATETIME NOT NULL,
  `appointment_time` TIME NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 1,
  `patients_id` INT NOT NULL,
  `dentists_id` INT NOT NULL,
  `created_user_id` INT NOT NULL,
  `treatments_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_appointments_patients`
    FOREIGN KEY (`patients_id`)
    REFERENCES `SunriseDentalClinic`.`patients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_dentists1`
    FOREIGN KEY (`dentists_id`)
    REFERENCES `SunriseDentalClinic`.`dentists` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_users1`
    FOREIGN KEY (`created_user_id`)
    REFERENCES `SunriseDentalClinic`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_treatments1`
    FOREIGN KEY (`treatments_id`)
    REFERENCES `SunriseDentalClinic`.`treatments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_appointments_patients_idx` ON `SunriseDentalClinic`.`appointments` (`patients_id` ASC) VISIBLE;

CREATE INDEX `fk_appointments_dentists1_idx` ON `SunriseDentalClinic`.`appointments` (`dentists_id` ASC) VISIBLE;

CREATE INDEX `fk_appointments_users1_idx` ON `SunriseDentalClinic`.`appointments` (`created_user_id` ASC) VISIBLE;

CREATE INDEX `fk_appointments_treatments1_idx` ON `SunriseDentalClinic`.`appointments` (`treatments_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `SunriseDentalClinic`.`invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SunriseDentalClinic`.`invoice` ;

CREATE TABLE IF NOT EXISTS `SunriseDentalClinic`.`invoice` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `treatment_cost` DOUBLE NOT NULL,
  `consultation_fee` DOUBLE NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 1,
  `total_amount` DOUBLE NOT NULL,
  `appointments_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_invoice_appointments1`
    FOREIGN KEY (`appointments_id`)
    REFERENCES `SunriseDentalClinic`.`appointments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_invoice_appointments1_idx` ON `SunriseDentalClinic`.`invoice` (`appointments_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
