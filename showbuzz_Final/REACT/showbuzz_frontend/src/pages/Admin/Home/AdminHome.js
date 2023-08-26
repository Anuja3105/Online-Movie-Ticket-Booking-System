import { useNavigate } from "react-router-dom";

import "./AdminHome.css";
function AdminHome() {
  const logoutUser = () => {
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("firstName");
    sessionStorage.removeItem("lastName");
    sessionStorage.removeItem("loginStatus");
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("movie_name");
    sessionStorage.removeItem("movie_id");
    sessionStorage.removeItem("movie_poster");
    sessionStorage.removeItem("booking_date");
    sessionStorage.removeItem("booking_time");
    sessionStorage.removeItem("screen_no");
    sessionStorage.removeItem("theatre_name");
    sessionStorage.removeItem("theatre_address");
    sessionStorage.removeItem("cost_factor");
    sessionStorage.removeItem("checkedSeats");
    sessionStorage.removeItem("selectedSeats");
    sessionStorage.removeItem("totalCost");

    navigate("/");
  };

  var currentDate = new Date();
  var displayDate = currentDate.toDateString();

  const navigate = useNavigate();

  return (
    <div>
      <hr />
      <hr />
      <div className="row">
        <div className="col">
          <div className="float-start">
            <div className="time" style={{ boxShadow: "4px 4px 5px gray" }}>
              {displayDate}
            </div>
          </div>

          <div className="float-end">
            <div
              className="btn-group"
              role="group"
              style={{ boxShadow: "4px 4px 5px gray" }}
            >
              <button
                id="btnGroupDrop1"
                type="button"
                className="btn btn-primary "
                onClick={() => {
                  sessionStorage.removeItem("id");
                  sessionStorage.removeItem("firstName");
                  sessionStorage.removeItem("lastName");
                  sessionStorage.removeItem("loginStatus");
                  sessionStorage.removeItem("role");

                  navigate("/");
                }}
              >
                Logout
              </button>
            </div>
          </div>
        </div>
        <br />
        <hr />
        {/* <div className="col">
          <button
            onClick={() => {
              sessionStorage.removeItem("id");
              sessionStorage.removeItem("firstName");
              sessionStorage.removeItem("lastName");
              sessionStorage.removeItem("loginStatus");
              sessionStorage.removeItem("role");

              navigate("/");
            }}
            className="btn btn-danger"
            style={{
              backgroundColor: "transparent",
              color: "red",
              width: "10em",
            }}
          >
            Logout
          </button>
          <div className="float-start">
            <div className="time" style={{ boxShadow: "4px 4px 5px gray" }}>
              {displayDate}
            </div>
          </div>
        </div> */}
        <div className="row">
          <div className="col"></div>
          <div className="col shadow p-3 mb-5 bg-body rounded">
            <h2 style={{ textAlign: "center" }}>Welcome Admin</h2>
            <hr />
            <br />
            <div className="admin-home">
              <div class="d-grid gap-2">
                <button
                  class="btn btn-primary"
                  type="button"
                  onClick={() => {
                    navigate("/adallmovie");
                  }}
                >
                  Manage Movie
                </button>

                <button
                  class="btn btn-primary"
                  type="button"
                  onClick={() => {
                    navigate("/adallcity");
                  }}
                >
                  Manage City
                </button>

                <button
                  class="btn btn-primary"
                  type="button"
                  onClick={() => {
                    navigate("/adalltheatre");
                  }}
                >
                  Manage Theatre
                </button>
              </div>
            </div>
          </div>
          <div className="col"></div>
        </div>
      </div>
    </div>
  );
}

export default AdminHome;
