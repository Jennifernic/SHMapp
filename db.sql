/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - java_attendance_management
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`java_attendance_management` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `java_attendance_management`;

/*Table structure for table `assign` */

DROP TABLE IF EXISTS `assign`;

CREATE TABLE `assign` (
  `assign_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`assign_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `assign` */

/*Table structure for table `attendance` */

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`attendance_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `attendance` */

insert  into `attendance`(`attendance_id`,`request_id`,`date`,`status`) values 
(8,18,'2023-03-03','present'),
(7,16,'2023-03-03','present'),
(6,15,'2023-03-03','absent'),
(9,19,'2023-03-03','absent'),
(10,20,'2023-03-03','absent');

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`message_id`,`sender_id`,`receiver_id`,`message`,`date`) values 
(1,2,3,'Yj','2023-01-04'),
(2,3,2,'Hloo','2023-01-04'),
(3,2,3,'Hi','2023-01-04'),
(4,3,2,'Hello','2023-01-04'),
(5,2,3,'Hllo','2023-01-04'),
(6,3,2,'Hiii','2023-01-04'),
(7,3,2,'Hiii','2023-01-04'),
(8,3,2,'Hi Aleena Teacher','2023-01-04'),
(9,2,3,'Hello','2023-01-04'),
(10,2,3,'Hello','2023-01-04'),
(11,3,2,'Aleena Kuttuuuuu','2023-01-04'),
(12,2,3,'Hello Anju Kuttan','2023-01-04'),
(13,3,2,'Sugam Ano','2023-01-04'),
(14,2,3,'Hello Anjuzzzz','2023-01-04'),
(15,3,2,'Luttapiii','2023-01-04'),
(16,2,3,'Hii Luttapiiii ','2023-01-04'),
(17,3,2,'Sreethu Kuttan Nthey ','2023-01-04'),
(18,3,2,'Avn Akke Shokam Annu','2023-01-04'),
(19,2,3,'Mayavii Evidennu ','2023-01-04'),
(20,3,2,'Ninod Nthelum Parajao','2023-01-04'),
(21,2,3,'Ayo Daaa ','2023-01-04'),
(22,2,3,'Ayo Daaa ','2023-01-04'),
(23,3,2,'Mayavii Officill Anuu','2023-01-04'),
(24,2,3,'??','2023-01-04'),
(25,3,2,'Luttappi ?????????? Kannikuvano','2023-01-04'),
(26,3,2,'Luttappi ?????????? Kannikuvano','2023-01-04'),
(27,2,3,'??','2023-01-04'),
(28,3,2,'Avida','2023-01-04'),
(29,2,3,'??','2023-01-04'),
(30,2,3,'??','2023-01-04'),
(31,3,2,'https://media.giphy.com/media/3o6ZsXlmUb4S205hf2/giphy.gif ','2023-01-04'),
(32,3,2,'??????','2023-01-04'),
(33,2,3,'Ahhh Kanichuuu Thaaaaa Ammachiiii ','2023-01-04'),
(34,3,2,'Ninak Nthadiee Kannadath','2023-01-04'),
(35,3,2,'Babu Where Chare','2023-01-04'),
(36,2,3,'Enim u Luttapiiii Yee Kanichuuu Thaaaaa ','2023-01-04'),
(37,3,2,'Where Chare','2023-01-04'),
(38,3,2,'Shorunu','2023-01-04'),
(39,2,3,'Wherechare Babu Bayiii ','2023-01-04'),
(40,3,2,'Hi Babuu','2023-01-04'),
(41,2,3,'Hiii Babu Kutta','2023-01-04'),
(42,3,2,'Hii','2023-01-04');

/*Table structure for table `enquiry` */

DROP TABLE IF EXISTS `enquiry`;

CREATE TABLE `enquiry` (
  `enquiry_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `enquiry` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enquiry_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `enquiry` */

insert  into `enquiry`(`enquiry_id`,`student_id`,`enquiry`,`reply`,`date`) values 
(1,3,'Hhhh','pending','2023-03-02'),
(2,1,'assd','pending','2023-03-02'),
(3,1,'ty','pending','2023-03-02'),
(4,1,'asdf','pending','2023-03-02'),
(5,1,'doubt','pending','2023-03-03'),
(6,1,'hello we have a doubt','pending','2023-03-03'),
(7,1,'i have an enquiry','pending','2023-03-03'),
(8,1,'i have a enqu','pending','2023-03-03'),
(9,3,'we have a doubt ','pending','2023-03-03'),
(10,3,'doubt ','okay....','2023-03-03');

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) DEFAULT NULL,
  `event` varchar(100) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `venue` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `hour` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `event` */

insert  into `event`(`event_id`,`teacher_id`,`event`,`time`,`date`,`venue`,`category`,`hour`) values 
(14,2,'new event ','4','3/3/2023','tshs','fixed',5),
(13,NULL,'new event 2','7:30','8/3/2023','kumbalam','flexible',4),
(16,2,'samrudhi','10','15/3/2023','kadavanthra','fixed',4),
(15,2,'new new','10.30','14/3/2023','aluva','onetime',4),
(10,2,'qqq','666','34','fgg','flexible',3),
(11,2,'asdf','44','2/3/2023','fgh','flexible',6),
(12,2,'beach cleaning ','7','17/3/2023','fort Kochi ','fixed',2);

/*Table structure for table `external_activities` */

DROP TABLE IF EXISTS `external_activities`;

CREATE TABLE `external_activities` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `hrs` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `external_activities` */

insert  into `external_activities`(`activity_id`,`student_id`,`name`,`desc`,`hrs`,`image`,`status`) values 
(1,NULL,'sfghd','sghj','sgah',NULL,'gavhbmn,'),
(2,NULL,'asdff','Descriptioneryui','3','01_03_2023_07_38_59.jpg','pending'),
(3,NULL,'dgh','Description','4','01_03_2023_07_44_35.jpg','pending'),
(4,NULL,'ddf','sdf','4','01_03_2023_07_50_00.jpg','pending'),
(5,NULL,'rty','dfg','56','02_03_2023_11_53_03.jpg','pending'),
(6,NULL,'asd','zxc','9','02_03_2023_11_54_09.jpg','pending'),
(7,NULL,'asd','zxc','9','02_03_2023_11_54_14.jpg','pending'),
(8,NULL,'home care','care','4','02_03_2023_12_41_47.jpg','verified'),
(9,NULL,'adt','xgy','4','02_03_2023_01_39_02.jpg','pending'),
(10,NULL,'new 2','orphanage ','3','03_03_2023_01_37_18.jpg','verified'),
(11,NULL,'new 2','new event for students','5','03_03_2023_01_43_39.jpg','pending'),
(12,1,'new event 5','new event for college','4','03_03_2023_03_37_58.jpg','pending'),
(13,1,'new evnt ','abcd','4','03_03_2023_03_49_35.jpg','verified'),
(14,1,'new 4','eveny organised','5','03_03_2023_03_54_16.jpg','pending'),
(15,1,'new','dheisk','6','03_03_2023_03_55_21.jpg','verified'),
(16,1,'last event ','shudbsj','7','03_03_2023_03_57_04.jpg','pending');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'amala','amala','teacher'),
(3,'ALU','ALU','student'),
(4,'sree','sree','teacher'),
(5,'rani','rani','student'),
(6,'nivi','nivi','student');

/*Table structure for table `notifications` */

DROP TABLE IF EXISTS `notifications`;

CREATE TABLE `notifications` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `notification` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `notifications` */

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `request` */

insert  into `request`(`request_id`,`student_id`,`event_id`,`date`,`status`) values 
(20,3,16,'2023-03-03','pending'),
(19,3,12,'2023-03-03','pending'),
(18,1,13,'2023-03-03','pending'),
(17,1,10,'2023-03-03','pending'),
(16,1,11,'2023-03-03','pending'),
(15,1,12,'2023-03-03','pending');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`student_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`) values 
(1,3,'Aleena','John','Vypin','9988776655','aleena@gmail.com'),
(2,5,'rani','j','kadavanthra','9988554433','rani@gmail.com'),
(3,6,'nivi','nivi','ernakulam','9898766767','asdf@gmail.com');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `teacher` */

insert  into `teacher`(`teacher_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`,`qualification`,`designation`) values 
(1,2,'Amala','k','kochi','9988776655','amala@gmail.com','btech','prof.'),
(2,4,'sreethu','l','pala','9977665544','sree@gmail.com','msc','prof.');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
