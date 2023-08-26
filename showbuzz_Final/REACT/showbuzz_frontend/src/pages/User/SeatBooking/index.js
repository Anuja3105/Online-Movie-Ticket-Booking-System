import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from "../../../config";
import "./index.css";

function SeatBooking() {
  const { state } = useLocation();
  const show_id = state.id;
  // const movie_id = state.show.movie_id;
  const { id } = sessionStorage;
  const navigate = useNavigate();
  const [seats, setSeats] = useState([]);
  const [checked, setChecked] = useState([]);
  const [bookedSeats, setBookedSeats] = useState([]);
  const [cost, setCost] = useState([]);
  const { booking_date, cost_factor } = sessionStorage;

  useEffect(() => {
    // const url = `${URL}/seat/all`;
    const url =
      `${URL}/seat/unbooked?show_id=` +
      show_id +
      `&booking_date=` +
      booking_date;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setSeats(result["data"]);
      } else {
        toast.warning(result["error"]);
      }
    });
    const url2 =
      `${URL}/seat/booked?show_id=` + show_id + `&booking_date=` + booking_date;
    axios.get(url2).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setBookedSeats(result["data"]);
      } else {
        toast.warning(result["error"]);
      }
    });
  }, []);

  /* Creating rows for theatre */
  var row1 = [];
  var row2 = [];
  var row3 = [];

  /* Adding Seats into rows */
  seats.map((s) => {
    if (s.seat_type === "SILVER") {
      row1.push(s);
    } else if (s.seat_type === "GOLD") {
      row2.push(s);
    } else {
      row3.push(s);
    }
  });

  var selectedSeats = [];

  var totalCost = 0;

  /* Adding Seats into array of Checked seats */
  const handleCheck = (event) => {
    var updatedList = [...checked];
    var updatedCost = [...cost];
    if (event.target.checked) {
      updatedList = [...checked, event.target.id];
      updatedCost = [...cost, event.target.value];
    } else {
      updatedList.splice(checked.indexOf(event.target.id), 1);
      updatedCost.splice(cost.indexOf(event.target.value), 1);
    }
    setChecked(updatedList);
    setCost(updatedCost);
  };

  /* Calculating total price */
  for (let i = 0; i < cost.length; i++) {
    totalCost += parseInt(cost[i]);
  }

  const checkedItems = checked.length
    ? checked.reduce((total, item) => {
        return total + ", " + item;
      })
    : "";

  var isChecked = (item) =>
    checked.includes(item)
      ? ("checked-item", selectedSeats.push(item))
      : "not-checked-item";

  return (
    <div>
      <h2 style={{ textAlign: "center" }}>Seat Booking</h2>
      <hr />
      <div>
        <div className="container">
          <div className="row">
            <h2>Available Seats : </h2>
            <hr />
            {row1.map((item, index) => (
              <div key={index} className="col">
                <input
                  id={item?.seat_id}
                  value={item?.price * cost_factor}
                  type="checkbox"
                  onChange={handleCheck}
                />
                <span className={isChecked(item.seat_id)}>{item.seat_id}</span>
                <div>{item.price * cost_factor}</div>
              </div>
            ))}
          </div>
        </div>
        <hr />
        <div className="container">
          <div className="row">
            {row2.map((item, index) => (
              <div key={index} className="col">
                <input
                  id={item?.seat_id}
                  value={item?.price * cost_factor}
                  type="checkbox"
                  onChange={handleCheck}
                />
                <span className={isChecked(item.seat_id)}>{item.seat_id}</span>
                <div>{item.price * cost_factor}</div>
              </div>
            ))}
          </div>
        </div>
        <hr />
        <div className="row">
          {row3.map((item, index) => (
            <div key={index} className="col">
              <input
                id={item.seat_id}
                value={item?.price * cost_factor}
                type="checkbox"
                onChange={handleCheck}
              />
              <span className={isChecked(item.seat_id)}>{item.seat_id}</span>
              <div>{item.price * cost_factor}</div>
            </div>
          ))}
        </div>
      </div>
      <hr />
      <div className="row">
        <h4>Selected seats :{checkedItems} </h4>
        <hr />
        <h4>Total Price : {totalCost} </h4>
        <hr />
        <div className="row">
          {/* <h4> Booked Seats : </h4>{" "} */}
          {bookedSeats.map((e) => {
            return <div className="col">{e}</div>;
          })}
        </div>
      </div>
      <button
        className="btn btn-success float-start"
        onClick={() => {
          {
            sessionStorage["checkedSeats"] = checkedItems;
            sessionStorage["selectedSeats"] = selectedSeats;
            sessionStorage["totalCost"] = totalCost;
          }
          navigate("/seatConfirm", {
            state: { id: show_id, seats: selectedSeats },
          });
        }}
      >
        Proceed
      </button>

      <div className="mb-3">
            <button
          onClick={() => {
            navigate("/show");
          }}
          type="button"
           class="btn btn-danger float-end"
        >
          Back
        </button>
        </div>
    </div>
  );
}

export default SeatBooking;
