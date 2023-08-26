import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { URL } from "../../../config";
import { useNavigate } from "react-router-dom";
import $ from "jquery";
import swal from "sweetalert";
import { Row, Col, UncontrolledCarousel, Container, Button } from "reactstrap";

const SeatConfirm = () => {
  const { state } = useLocation();
  const show_id = state.id;
  const {
    id,
    firstName,
    lastName,
    booking_time,
    checkedSeats,
    movie_name,
    movie_poster,
    screen_no,
    booking_date,
    theatre_name,
    theatre_address,
    totalCost1,
    selectedSeats,
  } = sessionStorage;

  var theatrename = sessionStorage.getItem("theatre_name");
  var theatre_address1 = sessionStorage.getItem("theatre_address");
  var screeNo = sessionStorage.getItem("screen_no");
  var selectedSeats1 = sessionStorage.getItem("selectedSeats");
  var totalCost = sessionStorage.getItem("totalCost");
  var cityName = sessionStorage.getItem("city_name");
  var showTime = sessionStorage.getItem("booking_time");

  console.log(theatre_address);
  console.log(theatrename);
  console.log(screeNo);
  console.log(selectedSeats);
  console.log(totalCost);

  var movieName = sessionStorage.getItem("movie_name");
  console.log(movieName);
  var theatre_id = sessionStorage.getItem("theatre_id");
  console.log(theatre_id);
  const navigate = useNavigate();

  const MakePayment = () => {
    console.log(booking_date);
    var seats = [];
    seats = selectedSeats.split(",");
    console.log(seats);
    // var amount = document.getElementById("totalAmount").innerHTML;
    var amount = totalCost;
    $.ajax({
      url: `${URL}/payment/createOrder`,
      data: JSON.stringify({ amount: amount, theatre_id: theatre_id }),
      contentType: "application/json",
      type: "POST",
      dataType: "json",
      success: function (response) {
        console.log(response);
        const order_id = response.id.toString();
        console.log(order_id);
        const url2 = `${URL}/theatre/${theatre_id}`;
        axios.get(url2).then((response1) => {
          const result1 = response1.data;
          if (result1["status"] == "success") {
            {
              const bookingId = [];
              seats.forEach((element) => {
                const body = {
                  show_id,
                  order_id: order_id,
                  seat_id: element,
                  user_id: id,
                  payment_status: 0,
                  booking_date,
                };
                const url = `${URL}/seat/insert`;
                axios.post(url, body).then((response4) => {
                  const result4 = response4.data;
                  console.log(result4);
                  if (result4["status"] == "success") {
                    toast.success("seat record added");
                    bookingId.push(result4.data.insertedId);
                    sessionStorage["bookingId"] = bookingId;
                  } else {
                    toast.warning("error");
                  }
                });
              });
            }
            console.log(result1);
            const { key1 } = result1["data"];
            console.log(key1);
            console.log(typeof amount);
            console.log(response.status);
            if (response.status == "created") {
              var options = {
                key: key1,
                amount: response.amount,
                currency: "INR",
                name: "ShowBuzz",
                description: "Show Booking",
                image: "",
                order_id: response.id,
                user_id: response.user_id,
                handler: function (response) {
                  const url = `${URL}/paymentsucess`;
                  const data = {
                    order_id: response.razorpay_order_id,
                    signature: response.razorpay_signature,
                    payment_id: response.razorpay_payment_id,
                    userId: id,
                    amount: amount,
                  };
                  console.log(data);
                  axios.post(url, data).then((response1) => {
                    const result2 = response1.data;
                    if ((result2["status"] = "success")) {
                      swal(
                        "Thank You",
                        "Your Payment Successfully Done!",
                        "success"
                      );
                      const datetime = result2.data.payment_timeStamp;
                      console.log(typeof datetime);
                      const datetime1 = new Date(datetime);
                      console.log(datetime1.toLocaleString());
                      console.log(typeof datetime1);
                      sessionStorage["datetime"] = datetime1.toLocaleString();
                      sessionStorage["payment_id"] =
                        response.razorpay_payment_id;
                      const responseOrder_id = response.razorpay_order_id;
                      sessionStorage["order_id"] = response.razorpay_order_id;
                      const url5 = `${URL}/seat/`;
                      const data = {
                        responseOrder_id: responseOrder_id,
                      };
                      axios.put(url5, data).then((response5) => {
                        const result5 = response5.data;
                        console.log(result5);
                        if (result5["status"] == "success") {
                          toast.success("Ticket Booked");
                        } else {
                          toast.warning("error");
                        }
                      });
                      navigate("/thankYou");
                    }
                  });

                  console.log("order_id" + response.razorpay_order_id);
                  console.log("signature" + response.razorpay_signature);
                  console.log(response);
                  console.log(options);
                },
              };
            }
            var rzp1 = new window.Razorpay(options);
            rzp1.on("payment.failed", function (response) {
              console.log("error");
              swal("Failed", "Payment Failed", "error");
              console.log(response.razorpay_signature);
              console.log(response);
            });
            rzp1.open();
          }
        });
      },
      error: function (error) {
        console.log(error);
      },
    });
  };

  return (
    <div>
      <h2 style={{ textAlign: "center" }}>Order Details</h2>
      <hr />
      <Container style={{ backgroundColor: "aqua", marginTop: "40px" }}>
        <hr />
        <Container
          style={{
            textAlign: "center",
            backgroundColor: "white",
            marginBottom: "50px",
          }}
        >
          <h5>Movie Name :- {movieName}</h5>
          <h5>
            Theatre Name: {theatrename},{theatre_address},{cityName}{" "}
          </h5>
          <h5>Show Time : {showTime}</h5>
          <h5>Screen No : {screeNo}</h5>
          <h5>Seat No : {selectedSeats}</h5>
          <h5>
            Total amount : <b id="totalAmount">{totalCost}.00</b>Rs
          </h5>
          <hr />

          <Button color="danger" onClick={()=> navigate("/thankYou")}>
           Book Ticket
          </Button>
          <br></br>
          <br></br>
          {/* <div className="mb-3">
            <button
          onClick={() => {
            navigate("/seatBooking");
          }}
          type="button"
           class="btn btn-danger"
        >
          Back
        </button>
        </div> */}
          <br />
          <hr />
        </Container>
        <br />
      </Container>
    </div>
  );
};

export default SeatConfirm;
