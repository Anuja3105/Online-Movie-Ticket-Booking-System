import axios from "axios";
import { URL } from "../config";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";

const TheatreList = (props) => {
  const { theatre } = props;
  const navigate = useNavigate();
  const { movie_id } = sessionStorage;

  var current_date = new Date();
  var next_date = new Date();
  next_date.setDate(new Date().getDate() + 1);
  var next_next_date = new Date();
  next_next_date.setDate(new Date().getDate() + 2);

  // console.log(theatre);
  // console.log(theatre.screenList[0]);
  // console.log(current_date.toDateString());
  // console.log(next_date.toDateString());
  // console.log(next_next_date.toDateString());

  return (
    <div key={theatre?.theatre_id}>
      <div className="row">
        <div className="col">
          <div>
            {" "}
            {(theatre?.screenList).map((screen) => {
              return (
                <div key={screen?.id}>
                  {screen.showList.map((show) => {
                    {
                      if (show.movie_id == movie_id) {
                        return (
                          <div key={show?.id}>
                            <hr />
                            <table className="table">
                              <tbody>
                                <tr>
                                  {console.log("movie_id = " + show.movie_id)}
                                  <td scope="row">
                                    <h5> {theatre?.name} </h5>
                                    <h5> {theatre?.address} </h5>
                                  </td>
                                  <td>Screen No : {screen?.screen_no}</td>{" "}
                                  <td key={show?.id}>
                                    <button
                                      className="btn btn-primary"
                                      onClick={() => {
                                        sessionStorage["booking_time"] =
                                          show.showtime;
                                        sessionStorage["screen_no"] =
                                          screen.screen_no;
                                        sessionStorage["theatre_name"] =
                                          theatre.name;
                                        sessionStorage["theatre_address"] =
                                          theatre.address;
                                        sessionStorage["cost_factor"] =
                                          show.cost_factor;
                                        sessionStorage["theatre_id"] =
                                          theatre.theatre_id;
                                        sessionStorage["show_id"] = show.id;
                                        navigate("/seatBooking", {
                                          state: { id: show.id },
                                        });
                                      }}
                                    >
                                      {show.showtime}{" "}
                                    </button>
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        );
                      }
                    }
                  })}{" "}
                  {/* <hr /> */}
                </div>
              );
            })}{" "}
          </div>
          <hr />
        </div>
      </div>
    </div>
  );
};

export default TheatreList;
