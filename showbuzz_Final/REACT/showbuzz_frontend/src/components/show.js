import axios from "axios";
import { URL } from "../config";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";

const ShowList = (props) => {
  const { show } = props;
  const currentUserId = sessionStorage["id"];
  const navigate = useNavigate();
  //==============================================

  return (
    <div key={show?.id}>
      <div className="row">
        <div className="col">
          {/* <h6> {show?.id} </h6>
          <h6> {show?.screen_id} </h6>
          <h6> {show?.movie_id} </h6> */}
          <h6> {show?.showdate} </h6>
          <h6> {show?.showtime} </h6>
          <hr />
        </div>
      </div>
    </div>
  );
};

export default ShowList;
