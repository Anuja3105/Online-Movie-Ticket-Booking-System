import { toBeRequired } from "@testing-library/jest-dom/dist/matchers";
import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Label } from "reactstrap";
import { URL } from "../../../config";
const Booking = () => {
  const { state } = useLocation();
  const { show_id } = state;
  const [bookingDate, setBookingDate] = useState({});
  const [seats, setSeats] = useState([]);
  const [useats, setUSeats] = useState([]);
  const [allSeats, setAllSeats] = useState([]);
  const navigate = useNavigate();

  /* Creating rows for theatre */
  //   var row1 = [];
  //   var row2 = [];
  //   var row3 = [];

  //   /* Adding Seats into rows */
  //   allSeats.map((s) => {
  //     if (s.seat_type === "SILVER") {
  //       row1.push(s);
  //     } else if (s.seat_type === "GOLD") {
  //       row2.push(s);
  //     } else {
  //       row3.push(s);
  //     }
  //   });

  const loadBookedSeats = () => {
    const url =
      `${URL}/seat/booked?show_id=` + show_id + `&booking_date=` + bookingDate;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setSeats(result["data"]);
      }
    });
  };

  //   const loadAllSeats = () => {
  //     const url = `${URL}/seat/all`;
  //     axios.get(url).then((response) => {
  //       const result = response.data;
  //       if (result["status"] == "success") {
  //         setAllSeats(result["data"]);
  //       }
  //     });
  //   };

  const UnbookedSeats = () => {
    const url =
      `${URL}/seat/unbooked?show_id=` +
      show_id +
      `&booking_date=` +
      bookingDate;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setUSeats(result["data"]);
      }
    });
  };

  console.log(useats);

  //   useEffect(() => {
  //     loadAllSeats();
  //   }, []);

  return (
    <div className="container">
      <h2 style={{ textAlign: "center" }}>Booking Status</h2>
      <hr />
      <div>
        <button
          className="btn btn-danger"
          onClick={() => {
            navigate("/manageShows");
          }}
        >
          Back
        </button>
      </div>
      <hr />
      <div className="row">
        <div className="col"></div>
        <div className="col mb-3" style={{ textAlign: "center" }}>
          <Label className="label-form">Choose Date</Label>
          <input
            type="date"
            className="form-control"
            onChange={(e) => {
              setBookingDate(e.target.value);
            }}
            required
          />
          <br />
          <button
            className="btn btn-primary"
            onClick={() => {
              loadBookedSeats();
              UnbookedSeats();
            }}
          >
            Check
          </button>
        </div>
        <div className="col"></div>
        <hr />
        <div className="row">
          <h4>Booked Seats : </h4>
          {seats.map((s) => {
            return <div className="col">{s}</div>;
          })}
        </div>
        <hr />
        <div className="row">
          <h4>Un-Booked Seats : </h4>
          {useats.map((u) => {
            return <div className="col">{u.seat_id}</div>;
          })}
        </div>
        <hr />

        {/* <div className="row">
          {row1.map((s) => {
            return (
              <div className="col">
                {seats.map((m) => {
                  return (
                    <div>
                      {s.seat_id != m ? (
                        <div>{s.seat_id}</div>
                      ) : (
                        <div style={{ backgroundColor: "red" }}>
                          {s.seat_id}
                        </div>
                      )}
                    </div>
                  );
                })}
              </div>
            ); */}
        {/* // seats.forEach((e) =>{" "} */}
        {/* {
              if (s.seat_id == e) {
                return (
                  <div className="col" style={{ backgroundColor: "red" }}>
                    {s.seat_id}
                    {console.log(e)}
                  </div>
                );
              } else {
                return <div className="col">{s.seat_id}</div>;
              }
            })
        } */}
        {/* )} */}
        {/* </div>
        <hr />
        <div className="row">
          {row2.map((s) => {
            return <div className="col">{s.seat_id}</div>;
          })}
        </div>
        <hr />
        <div className="row">
          {row3.map((s) => {
            return <div className="col">{s.seat_id}</div>;
          })}
        </div>
        <hr /> */}
      </div>
    </div>
  );
};

export default Booking;
