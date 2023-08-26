create database showbuzz;
use showbuzz;

create table User(
    user_id integer primary key auto_increment,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    phone_no VARCHAR(100),
    password VARCHAR(100),
    address_line VARCHAR(500),
    city varchar(100),
    state varchar(100),
    country varchar(100),
    pincode integer,
    role varchar(50),
    gender varchar(20),
    unique(email)
);

create table Movie(
    movie_id integer primary key auto_increment,
    movie_name VARCHAR(100),
    movie_details VARCHAR(200),
    category VARCHAR(100),
    language VARCHAR(100),
    release_date date,
    status integer,
    movie_poster varchar(200),
    genre varchar(200)
);


create table Movie_Rating(
    movie_rating_id integer primary key auto_increment,
    movie_id integer,
    user_id integer,
    rating float,
    foreign key (movie_id) references Movie(movie_id),
    foreign key (user_id) references User(user_id)
);

create table Movie_Review(
    movie_reveiw_id integer primary key auto_increment,
    movie_id integer,
    user_id integer,
    review VARCHAR(1024),
    foreign key (movie_id) references Movie(movie_id),
    foreign key (user_id) references User(user_id)
);


create table City(
    city_id integer primary key auto_increment,
    city_name VARCHAR(100),
    city_state VARCHAR(100),
    city_pincode integer
);

create table Theatre(
    theatre_id integer primary key auto_increment,
    name VARCHAR(50),
    city_id integer,
    user_id integer,
    address VARCHAR(200),
    no_of_screen integer,
    key1 varchar(500), key2 varchar(500), foreign key(user_id) references User(user_id),
    foreign key (city_id) references City(city_id)
);

create table Screen(
    screen_id integer primary key auto_increment,
    screen_no integer,
    theatre_id integer,
    foreign key (theatre_id) references Theatre(theatre_id)
);

create table Shows(
    show_id int primary key auto_increment,
    movie_id int,
    screen_id int,
    cost_factor float,
    showdate date,
    showtime time,
    foreign key (movie_id) references Movie(movie_id),
    foreign key (screen_id) references Screen(screen_id)
);




create table Seat(
    seat_id VARCHAR(5) primary key,
    seat_type VARCHAR(25),
    price float
);

create table Seat_Booking(
    booking_id integer primary key auto_increment,
    show_id int,
    seat_id VARCHAR(5),
    user_id int,
    booking_date date,
    created_timestamp timestamp default CURRENT_TIMESTAMP,
    order_id VARCHAR(500),
    payment_status int,
    foreign key (show_id) references Shows(show_id),
    foreign key (user_id) references User(user_id), 
    foreign key (seat_id) references Seat(seat_id)
);



create table Payment(
    payment_id integer primary key auto_increment,
    razorpay_payment_id VARCHAR(500),
    order_id VARCHAR(500),
    signature VARCHAR(500),
    user_id int,
    totalAmount float,
    payment_timestamp  timestamp default CURRENT_TIMESTAMP,
    foreign key (user_id) references User(user_id)
);


create table Orders(
    order_id integer primary key auto_increment,
    booking_id integer,
    user_id integer,
    payment_id integer,
    movie_id  integer,
    theatre_id integer,
    screen_id integer,
    show_id integer,
    seat_id VARCHAR(5),
    show_date date,
    show_time time,
    foreign key (user_id) references User(user_id),
    foreign key (payment_id) references Payment(payment_id),
    foreign key (movie_id) references Movie(movie_id),
    foreign key (theatre_id) references Theatre(theatre_id),
    foreign key (screen_id) references Screen(screen_id),
    foreign key (show_id) references Shows(show_id),
    foreign key (seat_id) references Seat(seat_id),
    foreign key (booking_id) references Seat_Booking(booking_id)
);
