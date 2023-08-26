import { useNavigate, useNavigationType } from "react-router-dom";
import thankYou from "../../../components/static/images/thank_you.jpg";
const ThankYou = () => {
  sessionStorage.removeItem("movie_name");
  sessionStorage.removeItem("movie_id");
  sessionStorage.removeItem("movie_poster");
  sessionStorage.removeItem("booking_date");
  sessionStorage.removeItem("booking_time");
  sessionStorage.removeItem("screen_no");
  sessionStorage.removeItem("theatre_name");
  sessionStorage.removeItem("theatre_id");
  sessionStorage.removeItem("theatre_address");
  sessionStorage.removeItem("cost_factor");
  sessionStorage.removeItem("checkedSeats");
  sessionStorage.removeItem("selectedSeats");
  sessionStorage.removeItem("totalCost");
  sessionStorage.removeItem("payment_id");
  sessionStorage.removeItem("order_id");
  sessionStorage.removeItem("bookingId");

  const navigate = useNavigate();

  

 
  return (
    <div>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          
          <img src={thankYou} alt="Thank You" />
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
};

export default ThankYou;
