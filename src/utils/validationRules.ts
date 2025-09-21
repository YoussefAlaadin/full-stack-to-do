// validationRules.ts
export const VALIDATION_RULES = {
  username: {
    required: "Username is required",
    minLength: {
      value: 3,
      message: "Username must be at least 3 characters long"
    },
    maxLength: {
      value: 20,
      message: "Username must not exceed 20 characters"
    },
   
  },
  
  email: {
    required: "Email is required",
    pattern: {
      value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
      message: "Please enter a valid email address"
    }
  },
  //d#dWA;55aw82a@
  password: {
    required: "Password is required",
    minLength: {
      value: 8,
      message: "Password must be at least 8 characters long"
    },
    pattern: {
      value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]/,
      message: "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    }
  },
  
  // You can add more field rules here as needed
  confirmPassword: {
    required: "Please confirm your password"
    // The validate function will be added dynamically
  }
};