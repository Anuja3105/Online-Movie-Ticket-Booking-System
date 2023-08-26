import "./App.css";
import React, { useState } from "react";
import { BrowserRouter, Route, Routes, Link } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Signin from "./pages/Signin";
import Signup from "./pages/Signup";
import Home from "./pages/User/Home";
import Profile from "./pages/User/Profile";
import Show from "./pages/User/Show";
import SeatBooking from "./pages/User/SeatBooking";
import SeatConfirm from "./pages/User/SeatConfirm";
import City from "./pages/User/City";
import AdAllMovie from "./pages/Admin/AdAllMovie";
import EditMovie from "./pages/Admin/EditMovie";
import AddMovie from "./pages/Admin/AddMovie";
import AddCity from "./pages/Admin/City/AddCity";
import AdAllCity from "./pages/Admin/City/AdAllCity";
import EditCity from "./pages/Admin/City/EditCity";
import AdAllTheatre from "./pages/Admin/Theatre/AdAllTheatre";
import EditTheatre from "./pages/Admin/Theatre/EditTheatre";
import AddTheatre from "./pages/Admin/Theatre/AddTheatre";
import AdAllScreen from "./pages/Admin/Screen/AdAllScreen";
import EditScreen from "./pages/Admin/Screen/EditScreen";
import AddScreen from "./pages/Admin/Screen/AddScreen";
import AdAllShow from "./pages/Admin/Show/AdAllShow";
import EditShow from "./pages/Admin/Show/EditShow";
import AddShow from "./pages/Admin/Show/AddShow";
import AdminHome from "./pages/Admin/Home/AdminHome";

import MoviePage from "./pages/User/Movie/MoviePage";
import ReviewPage from "./pages/User/MovieReview/ReviewPage";
import AddReview from "./pages/User/MovieReview/AddReview";
import AddRating from "./pages/User/MovieRating/AddRating";
import AllMovies from "./pages/User/Movie/Allmovies";
import TicketShow from "./pages/User/Ticket";
import ThankYou from "./pages/User/Ticket/ThankYou";
import EditProfile from "./pages/User/Profile/EditProfile";
import ManagerHome from "./pages/Manager/Home";
import MAddScreen from "./pages/Manager/Manage Screen/Add New Screen";
import MEditScreen from "./pages/Manager/Manage Screen/EditScreen";
import ManageScreens from "./pages/Manager/Manage Screen";
import MAddShow from "./pages/Manager/Manage Shows/AddShow";
import MEditShow from "./pages/Manager/Manage Shows/Edit Show";
import MManageShows from "./pages/Manager/Manage Shows";
import MyOrders from "./pages/User/MyOrders";
import Booking from "./pages/Manager/Booking";
import TheatreOrder from "./pages/Manager/Orders";

const AuthorizeUser = () => {
  const loginStatus = sessionStorage["loginStatus"];
  const user_role = sessionStorage["role"];
  // return loginStatus == "1" && user_role == "user" ? <Home /> : <Signin />;

  if (loginStatus == "1" && user_role == "user") {
    <Home />;
  } else if (loginStatus == "1" && user_role == "admin") {
    <AdminHome />;
  } else if (loginStatus == "1" && user_role == "manager") {
    <ManagerHome />;
  } else {
    <Signin />;
  }
};

function App() {
  return (
    <div className="container">
      <h1 className="title">Showbuzz.com</h1>

      <BrowserRouter>
        <Routes>
          {/* <Route path="/" element={<AuthorizeUser />} /> */}
          <Route path="/" element={<Signin />} />
          <Route path="/signup" element={<Signup />} />

          <Route path="/home" element={<Home />} />
          <Route path="/city" element={<City />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/myOrders" element={<MyOrders />} />
          <Route path="/editProfile" element={<EditProfile />} />
          <Route path="/show" element={<Show />} />
          <Route path="/seatBooking" element={<SeatBooking />} />
          <Route path="/seatConfirm" element={<SeatConfirm />} />
          {/*<Route path="/ticketShow" element={<TicketShow />} />*/}
          <Route path="/thankYou" element={<ThankYou />} />

          {/* Movie */}

          <Route path="/movie" element={<MoviePage />} />
          <Route path="/review" element={<ReviewPage />} />
          <Route path="/addreview" element={<AddReview />} />
          <Route path="/addrating" element={<AddRating />} />
          <Route path="/allmovie" element={<AllMovies />} />

          {/* Manager  */}
          <Route path="/managerHome" element={<ManagerHome />} />
          <Route path="/mAddScreen" element={<MAddScreen />} />
          <Route path="/mEditScreen" element={<MEditScreen />} />
          <Route path="/manageScreens" element={<ManageScreens />} />
          <Route path="/mAddShow" element={<MAddShow />} />
          <Route path="/mEditShow" element={<MEditShow />} />
          <Route path="/manageShows" element={<MManageShows />} />
          <Route path="/mBooking" element={<Booking />} />
          <Route path="/mOrders" element={<TheatreOrder />} />

          {/* Admin  */}
          <Route path="/adminHome" element={<AdminHome />} />

          <Route path="/adallmovie" element={<AdAllMovie />} />
          <Route path="/editmovie" element={<EditMovie />} />
          <Route path="/addmovie" element={<AddMovie />} />

          <Route path="/adallcity" element={<AdAllCity />} />
          <Route path="/editcity" element={<EditCity />} />
          <Route path="/addcity" element={<AddCity />} />

          <Route path="/adalltheatre" element={<AdAllTheatre />} />
          <Route path="/edittheatre" element={<EditTheatre />} />
          <Route path="/addtheatre" element={<AddTheatre />} />

          {/* <Route path="/adallScreen" element={<AdAllScreen />} />
          <Route path="/editscreen" element={<EditScreen />} />
          <Route path="/addscreen" element={<AddScreen />} />

          <Route path="/adallshow" element={<AdAllShow />} />
          <Route path="/editshow" element={<EditShow />} />
          <Route path="/addshow" element={<AddShow />} /> */}
        </Routes>
      </BrowserRouter>
      <ToastContainer theme="colored" />
    </div>
  );
}

export default App;
