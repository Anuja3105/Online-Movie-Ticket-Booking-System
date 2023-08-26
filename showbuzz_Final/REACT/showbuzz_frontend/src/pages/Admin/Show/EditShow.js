import axios from "axios";
import { useState } from "react";
import { useLocation } from "react-router";
import { useNavigate } from "react-router";

function EditShow(){

    const { state } = useLocation();
    const { show_detail } = state ;
    const show_id = state.show_detail.id;
    const navigate = useNavigate();
  

    const [movie_id,setMovieId] = useState(0);
    const [screen_id,setScreenId] = useState(0);
    const [showdate,setShowDate] = useState('');
    const [showtime,setShowTime] = useState('');

    
    function editShow(){
        const url = "http://localhost:8080/show/"+show_id;
        
            
            const body = {
                movie_id:movie_id,
                screen_id:screen_id,
                showdate:showdate,
                showtime:showtime,
  
            }

           
            axios.put(url,body).then((response) =>{
                const result = response.data;
                if(result['status'] == 'success'){
                    navigate('/adallshow');
                    console.log(result.data)
                }else{
                    console.log(result.error);
                }
            })
        
    }

    return (
        <div>
    
          <div className="row">
            <div className="col"></div>
            <div className="col">
            <h1 className="title">Edit Show</h1>
            <hr />
              <div className="form">
               
              <div className="mb-3">
                  <label htmlFor="" className="label-control">
                  Movie Id:
                  </label>
                  <input onChange={(e) => {
                      setMovieId(e.target.value)
                  }} type="number"  className="form-control"/>
                </div>
    
                <div className="mb-3">
                  <label htmlFor="" className="label-control">
                  Screen Id:
                  </label>
                  <input onChange={(e) => {
                      setScreenId(e.target.value)
                  }} type="number" className="form-control"/>
                </div>

                <div className="mb-3">
                  <label htmlFor="" className="label-control">
                  Show Date :
                  </label>
                  <input onChange={(e) => {
                      setShowDate(e.target.value)
                  }} type="date"  className="form-control"/>
                </div>
    
                <div className="mb-3">
                  <label htmlFor="" className="label-control">
                  Show Time :
                  </label>
                  <input onChange={(e) => {
                      setShowTime(e.target.value)
                  }} type="text"  className="form-control"/>
                </div>

               
                <div className="mb-3">
                  <button onClick={editShow} className="btn btn-primary">
                    Submit
                  </button>
                </div>
              </div>
            </div>
            <div className="col"></div>
          </div>
        </div>
      );

}


export default EditShow;