import React, { useState, useEffect } from 'react';

interface PasswordStrengthIndicatorProps {
  password: string;
  className?: string;
}

interface PasswordChecks {
  length: boolean;
  uppercase: boolean;
  lowercase: boolean;
  number: boolean;
  special: boolean;
}

const PasswordStrengthIndicator = ({ password, className = "" }: PasswordStrengthIndicatorProps) => {
  const [strength, setStrength] = useState(0);
  const [checks, setChecks] = useState<PasswordChecks>({
    length: false,
    uppercase: false,
    lowercase: false,
    number: false,
    special: false
  });

  useEffect(() => {
    const newChecks: PasswordChecks = {
      length: password.length >= 8,
      uppercase: /[A-Z]/.test(password),
      lowercase: /[a-z]/.test(password),
      number: /\d/.test(password),
      special: /[@$!%*?&]/.test(password)
    };
    
    setChecks(newChecks);
    setStrength(Object.values(newChecks).filter(Boolean).length);
  }, [password]);

  const getStrengthColor = () => {
    if (strength < 2) return 'bg-red-500';
    if (strength < 4) return 'bg-yellow-500';
    return 'bg-green-500';
  };

  const getStrengthText = () => {
    if (strength < 2) return 'Weak';
    if (strength < 4) return 'Medium';
    return 'Strong';
  };

  // Don't render if no password
  if (!password) return null;

  return (
    <div className={`mt-2 ${className}`}>
      {/* Strength Bar */}
      <div className="flex space-x-1 mb-2">
        {[1, 2, 3, 4, 5].map((level) => (
          <div
            key={level}
            className={`h-2 flex-1 rounded transition-colors duration-200 ${
              level <= strength ? getStrengthColor() : 'bg-gray-200'
            }`}
          />
        ))}
      </div>
      
      {/* Strength Text */}
      <p className="text-sm text-gray-600 mb-2">
        Password strength: <span className="font-medium">{getStrengthText()}</span>
      </p>
      
      {/* Requirements Checklist */}
      <ul className="text-xs text-gray-600 space-y-1">
        <li className={`flex items-center ${checks.length ? 'text-green-600' : 'text-red-600'}`}>
          <span className="mr-2">{checks.length ? '✓' : '✗'}</span>
          At least 8 characters
        </li>
        <li className={`flex items-center ${checks.uppercase ? 'text-green-600' : 'text-red-600'}`}>
          <span className="mr-2">{checks.uppercase ? '✓' : '✗'}</span>
          One uppercase letter
        </li>
        <li className={`flex items-center ${checks.lowercase ? 'text-green-600' : 'text-red-600'}`}>
          <span className="mr-2">{checks.lowercase ? '✓' : '✗'}</span>
          One lowercase letter
        </li>
        <li className={`flex items-center ${checks.number ? 'text-green-600' : 'text-red-600'}`}>
          <span className="mr-2">{checks.number ? '✓' : '✗'}</span>
          One number
        </li>
        <li className={`flex items-center ${checks.special ? 'text-green-600' : 'text-red-600'}`}>
          <span className="mr-2">{checks.special ? '✓' : '✗'}</span>
          One special character (@$!%*?&)
        </li>
      </ul>
    </div>
  );
};

export default PasswordStrengthIndicator;