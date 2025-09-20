import { useForm } from "react-hook-form";
import type { SubmitHandler } from "react-hook-form";
import Button from "../components/ui/Button";
import Input from "../components/ui/Input";
import { data } from "react-router-dom";
//import InputErrorMessage from "../components/InputErrorMessage";
//import { REGISTER_FORM } from "../data";
//import { yupResolver } from "@hookform/resolvers/yup";
// import { registerSchema } from "../validation";
// import axiosInstance from "../config/axios.config";
// import { toast } from "react-hot-toast";
//import { useState } from "react";
//import { AxiosError } from "axios";
//import { IErrorResponse } from "../interfaces";
//import { Link, useNavigate } from "react-router-dom";

interface IFormInput {
  username: string;
  email: string;
  password: string;
}

const RegisterPage = () => {
  const { register, handleSubmit } = useForm<IFormInput>();
  const onSubmit: SubmitHandler<IFormInput> = data => console.log(data)
  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl font-bold mb-4">Register</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <Input {...register("username", { required: "Username is required" })} placeholder="Username" />
        <Input {...register("email", { required: "Email is required" })} placeholder="Email"  />
        <Input {...register("password", { required: "Password is required" })} type="password" placeholder="Password" />
        <Button fullWidth >Register</Button>
      </form>
    </div>
  )
}
export default RegisterPage;