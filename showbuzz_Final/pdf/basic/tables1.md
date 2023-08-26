# TITLE : SHOWBUZZ

=============================================================================

```
Location 	- City_id, City_name, State_id
User 	- User_id, User_name, Age, Email, Password, City_id, 		contact_no, Gender.
Venues 	- City_id, Venue_id, Venue_name, Address
Shows 	- Venue_id, Show_id, Category, Show_title, Show_time, 		Rating, Language, Sub - Category (genre).
Seats	- Show_id, Venue_id, Seat_no, Availability.
Admin	- Admin_id, Admin_name, Email, Password.
Orders	- Order_id, Show_id, Venue_id, Seat_no, Receipt No.
```

## Database_name ==> MBSystem

## Table names:==>

1. Movies **(R)**:
2. Movie_Details:
3. Movie_Review:
4. Movie_Rating:
5. Movie_Likes **(R)**:
6. User:
7. Payments:
8. Ticket:
9. Theaters:
10. Theatre_Review **(R)**:
11. Screen **(R)**:
12. Chair:
13. Chair_Status:
14. Counter_Person **(R)**:
15. Site_feedback **(R)**:
16. Booking_History:
17. City

=============================================================================

### Table names with fields :==>

1.  Movies:**(R)**

    - movie_id
    - name
    - certification
    - release_date
    - end_date
    - description (cast + director_name)
    - status(trending / upcoming / outdated)

2.  Movie_Details:

    - movie_details_id
    - movie_id
    - genre
    - language
    - format(2D / 3D)

3.  Movie_Review:

    - mreview_id
    - movie_id
    - user_id
    - reviews

4.  Movie_Rating:

    - mrating_id
    - movie_id
    - user_id
    - rating

5.  Movie_Likes:**(R)**

    - mlikes_id
    - movie_id
    - user_id
    - status(liked / disliked)

6.  User:

    - user_id
    - first_name
    - last_name
    - address
    - contact_no
    - email
    - password
    - role(customer / admin / theater_manager /counter_person)**(R)**

7.  Payments:

    - payment_id
    - user_id
    - date
    - time
    - total_amount
    - payment_mode

8.  Ticket:

    - ticket_id
    - user_id
    - payment_id
    - movie_id
    - theater_id
    - screen_id
    - chair_id
    - date
    - time

9.  Theaters:
    - theater_id
    - name
    - address
    - City_id
    - no_of_screens
    - facilities_description
    - no_of_counters
    - user_id (to get theater manager details)
      <!-- Registered_GST_no ===>can be removed -->
10. Theatre_Review: **(R)**

    - treview_id
    - theater_id
    - user_id (to get customer id giving reviews)
    - reviews

11. Screen:

    - screen_id
    - theater_id
    - capacity

12. Chair:

    - chair_id
    - chair_type (silver / gold / platinum)
    - price
    - theater_id
    - screen_id

    * chair_status(booked / unbooked)

      <!-- // rows
      // columns -->

                * silver - A B C D
                * gold - E F G H
                * platinum - I J

<!-- // individual chair ka info kaha store karna hai???? -->

13. Chair_Status:**(R)**

    - chair_status_id(a1, a2 ....a10, d1, d2....d10)
    - chair_id
    - chair_status(booked / unbooked)

14. Counter_Person:**(R)**

    - cp_id
    - user_id
    - theater_id
    - counter_id
    - ticket_id

15. Site_feedback: **(R)**

    - sf_id
    - email
    - password
    - feedback

16. Booking_History:

    - bh_id
    - user_id
    - ticket_id

17. City:
    - City_id
    - city_name
    - City_state
    - City_pincode

<!-- //plays from bookMyshow can be added -->
