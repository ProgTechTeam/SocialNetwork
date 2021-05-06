import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login";
import Registration from "../views/Registration";
import Feed from "../views/Feed";
import Users from "../views/users/Users";
import AllUsers from "../views/users/AllUsers";
import Friends from "../views/users/Friends";
import Subscribers from "../views/users/Subscribers";
import Subscriptions from "../views/users/Subscriptions";
import Profile from "../views/Profile";

Vue.use(VueRouter);

function castIdToInt(route) {
  return {
    id: Number(route.params.id),
  };
}

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/registration",
    name: "Registration",
    component: Registration,
  },
  {
    path: "/users",
    component: Users,
    children: [
      {
        path: "/users/all",
        name: "AllUsers",
        component: AllUsers,
      },
      {
        path: "/users/:id/friends",
        name: "Friends",
        component: Friends,
        props: castIdToInt,
      },
      {
        path: "/users/:id/subscribers",
        name: "Subscribers",
        component: Subscribers,
        props: castIdToInt,
      },
      {
        path: "/users/:id/subscriptions",
        name: "Subscriptions",
        component: Subscriptions,
        props: castIdToInt,
      },
    ],
  },
  {
    path: "/profile/:id",
    name: "Profile",
    component: Profile,
    props: castIdToInt,
  },
  {
    path: "/feed",
    name: "Feed",
    component: Feed,
  },
];

const router = new VueRouter({
  routes,
});

export default router;
