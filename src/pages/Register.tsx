import { useForm } from "react-hook-form";
import type { SubmitHandler } from "react-hook-form";
import Button from "../components/ui/Button";
import Input from "../components/ui/Input";
import { useState } from "react";
import FormField from "../components/FormField";
import { VALIDATION_RULES } from "../utils/validationRules";
import PasswordStrengthIndicator from "../components/PasswordStrengthIndicator";
import { Link } from "react-router-dom";


interface IFormInput {
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
}

const RegisterPage = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');
  
  const { 
    register, 
    handleSubmit, 
    formState: { errors }, 
    watch,
    reset 
  } = useForm<IFormInput>();
  
  // Watch password for strength indicator and confirm password validation
  const watchPassword = watch("password", "");

  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    setIsLoading(true);
    setSuccessMessage('');
    
    try {
      // Simulate API call (replace with real API later)
      await new Promise(resolve => setTimeout(resolve, 2000));
      
      console.log('Registration data:', data);
      setSuccessMessage('Account created successfully! You can now sign in.');
      reset(); // Clear the form
      
    } catch (error) {
      console.error('Registration failed:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl font-bold mb-6 text-center">Create Account</h1>
      
      {/* Success Message */}
      {successMessage && (
        <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
          {successMessage}
        </div>
      )}
      
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
        
        {/* Username Field */}
        <FormField label="Username" error={errors.username?.message}>
          <Input 
            {...register("username", VALIDATION_RULES.username)} 
            placeholder="Enter your username" 
          />
        </FormField>

        {/* Email Field */}
        <FormField label="Email" error={errors.email?.message}>
          <Input 
            {...register("email", VALIDATION_RULES.email)} 
            type="email"
            placeholder="Enter your email" 
          />
        </FormField>

        {/* Password Field */}
        <FormField label="Password" error={errors.password?.message}>
          <Input 
            {...register("password", VALIDATION_RULES.password)} 
            type="password" 
            placeholder="Create a password" 
          />
          <PasswordStrengthIndicator password={watchPassword} />
        </FormField>

        {/* Confirm Password Field */}
        <FormField label="Confirm Password" error={errors.confirmPassword?.message}>
          <Input 
            {...register("confirmPassword", {
              ...VALIDATION_RULES.confirmPassword,
              validate: (value: string) => 
                value === watchPassword || "Passwords do not match"
            })} 
            type="password" 
            placeholder="Confirm your password" 
          />
        </FormField>

        {/* Submit Button */}
        <Button fullWidth isLoading={isLoading}>
          {isLoading ? 'Creating Account...' : 'Register'}
        </Button>
      </form>
      
      {/* Link to Login */}
      <p className="mt-6 text-center text-gray-600">
        Already have an account?{' '}
        <Link to="/login" className="text-blue-600 hover:underline font-medium">
          Sign in here
        </Link>
      </p>
    </div>
  );
};

export default RegisterPage;