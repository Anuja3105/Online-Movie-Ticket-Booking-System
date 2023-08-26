import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router";

function AddShow(){

    const navigate = useNavigate();


    const [movie_id,setMovieId] = useState(0);
    const [screen_id,setScreenId] = useState(0);
    const [cost_factor,setCostFactor] = useState(0);
    const [showdate,setShowDate] = useState('');
    const [showtime,setShowTime] = useState('');

    function addShow(){
        const url = "http://localhost:8080/show";
            if(movie_id == 0){
                console.log('entry movie_id');
            }else if(screen_id == 0){
                console.log('entry screen_id');   
            }else if(cost_factor == 0){
                console.log('entry screen_id');   
            }else if(showdate == ''){
                console.log('entry screen_id');   
            }else if(showtime == ''){
                console.log('entry screen_id');   
            }else{
                const body = {
                    movie_id:movie_id,
                    screen_id:screen_id,
                    cost_factor:cost_factor,
                    showdate:showdate,
                    showtime:showtime,
                }
    
                axios.post(url,body).then((response) => {
                    const result = response.data;
                    if(result['status'] == 'success'){
                        navigate('/adallshow');
                    }else{
                        console.log(result.error);
                    }
                })
            }
            
        }
        return (
            <div>
        
              <div className="row">
                <div className="col"></div>
                <div className="col">
                <h1 className="title">Add Show</h1>
                <hr />
                  <div className="form">
    
                  <div className="mb-3">
                      <label htmlFor="" className="label-control">
                      Movie Id:
                      </label>
                      <input onChange={(e) => {
                          setMovieId(e.target.value)
                      }} type="number" className="form-control"/>
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
                      Cost Factor :
                      </label>
                      <input onChange={(e) => {
                          setCostFactor(e.target.value)
                      }} type="number" className="form-control"/>
                    </div> 
    
                    <div className="mb-3">
                      <label htmlFor="" className="label-control">
                      Show Date:
                      </label>
                      <input onChange={(e) => {
                         setShowDate(e.target.value)
                      }} type="date" className="form-control"/>
                    </div> 
    
                    <div className="mb-3">
                      <label htmlFor="" className="label-control">
                      Show Time:
                      </label>
                      <input onChange={(e) => {
                          setShowTime(e.target.value)
                      }} type="text" className="form-control"/>
                    </div> 
    
    
                    <div className="mb-3">
                      <button onClick={addShow} className="btn btn-success">
                        Add
                      </button>
                    </div>
                  </div>
                </div>
                <div className="col"></div>
              </div>
            </div>
          )

}



export default AddShow;