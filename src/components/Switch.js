import React, { useState } from 'react';

function MySwitch() {
  const [isOn, setIsOn] = useState(false); // State to track switch state

  // Function to toggle the switch state
  const toggleSwitch = () => {
    setIsOn(!isOn);
  };

  return (
    <div onClick={toggleSwitch} style={{ cursor: 'pointer' }}>
      {isOn ? (
        <img src="/assets/misc/Switch_on.svg" alt="Switch On" />
      ) : (
        <img src="/assets/misc/Switch_off.svg" alt="Switch Off" />
      )}
    </div>
  );
}

export default MySwitch;
