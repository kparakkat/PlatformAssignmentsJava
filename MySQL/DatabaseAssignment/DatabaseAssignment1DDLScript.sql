-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DatabaseAssignment1
-- -----------------------------------------------------
-- Database Assignment Schema

-- -----------------------------------------------------
-- Schema DatabaseAssignment1
--
-- Database Assignment Schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DatabaseAssignment1` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `DatabaseAssignment1` ;

-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`User` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC) VISIBLE,
  UNIQUE INDEX `UserId_UNIQUE` (`UserId` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`UserProfile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`UserProfile` (
  `ProfileId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `FirstName` VARCHAR(30) NOT NULL,
  `LastName` VARCHAR(30) NOT NULL,
  `AddressLine1` VARCHAR(100) NULL,
  `AddressLine2` VARCHAR(100) NULL,
  `City` VARCHAR(30) NULL,
  `State` VARCHAR(30) NULL,
  `Country` VARCHAR(30) NULL,
  `Zip` VARCHAR(10) NULL,
  `Mobile` VARCHAR(10) NULL,
  PRIMARY KEY (`ProfileId`),
  UNIQUE INDEX `ProfileId_UNIQUE` (`ProfileId` ASC) VISIBLE,
  INDEX `UserId_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`AccountSetting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`AccountSetting` (
  `AccountSettingId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `SuspendStatus` BIT(1) NULL,
  `DaysToSuspend` INT NULL,
  `ReceiveNotification` BIT(1) NULL,
  PRIMARY KEY (`AccountSettingId`),
  INDEX `UserId_idx` (`UserId` ASC) INVISIBLE,
  CONSTRAINT `ASUserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`ExternalAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`ExternalAccount` (
  `ExternalAccountId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `ExternalAccountName` VARCHAR(30) NULL,
  `ExternalAccountLink` VARCHAR(100) NULL,
  PRIMARY KEY (`ExternalAccountId`),
  INDEX `UserId_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`Post` (
  `PostId` INT NOT NULL AUTO_INCREMENT,
  `PostName` VARCHAR(100) NOT NULL,
  `PostDescription` NVARCHAR(100) NOT NULL,
  `UserId` INT NOT NULL,
  PRIMARY KEY (`PostId`),
  INDEX `UserId_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`PostTag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`PostTag` (
  `PostTagId` INT NOT NULL AUTO_INCREMENT,
  `PostId` INT NOT NULL,
  `UserId` INT NOT NULL,
  PRIMARY KEY (`PostTagId`),
  INDEX `PostId_idx` (`PostId` ASC) VISIBLE,
  INDEX `UserId_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `PostId`
    FOREIGN KEY (`PostId`)
    REFERENCES `DatabaseAssignment1`.`Post` (`PostId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`Category` (
  `CategoryId` INT NOT NULL,
  `CategoryName` VARCHAR(50) NULL,
  `CategoryDescription` VARCHAR(100) NULL,
  PRIMARY KEY (`CategoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`PostCategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`PostCategory` (
  `PostCategoryId` INT NOT NULL,
  `PostId` INT NOT NULL,
  `CategoryId` INT NOT NULL,
  PRIMARY KEY (`PostCategoryId`),
  INDEX `PostId_idx` (`PostId` ASC) VISIBLE,
  INDEX `CategoryId_idx` (`CategoryId` ASC) VISIBLE,
  CONSTRAINT `PostId`
    FOREIGN KEY (`PostId`)
    REFERENCES `DatabaseAssignment1`.`Post` (`PostId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CategoryId`
    FOREIGN KEY (`CategoryId`)
    REFERENCES `DatabaseAssignment1`.`Category` (`CategoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`UserPostAction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`UserPostAction` (
  `UserPostActionId` INT NOT NULL,
  `UserId` INT NOT NULL,
  `PostId` INT NOT NULL,
  `ActionType` ENUM("Read", "Comment", "Favourite") NULL,
  `ActionComment` NVARCHAR(100) NULL,
  PRIMARY KEY (`UserPostActionId`),
  INDEX `PostId_idx` (`PostId` ASC) VISIBLE,
  INDEX `UserId_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `PostId`
    FOREIGN KEY (`PostId`)
    REFERENCES `DatabaseAssignment1`.`Post` (`PostId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DatabaseAssignment1`.`FollowUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DatabaseAssignment1`.`FollowUser` (
  `FollowUserId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `UserIdToFollow` VARCHAR(45) NULL,
  PRIMARY KEY (`FollowUserId`),
  INDEX `User_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserIdToFollow`
    FOREIGN KEY (`UserId`)
    REFERENCES `DatabaseAssignment1`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
