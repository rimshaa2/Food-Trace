/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

import React, { useState } from 'react';
import axios from 'axios';

const SignUpComponent = ({ setUser }) => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignUp = async () => {
        try {
            const response = await axios.post('/api/signup', { name, email, password });
            setUser(response.data);
        } catch (error) {
            console.error('Error signing up', error);
        }
    };

    return (
        <div>
            <h2>Sign Up</h2>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Name" />
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" />
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" />
            <button onClick={handleSignUp}>Sign Up</button>
        </div>
    );
};

export default SignUpComponent;

