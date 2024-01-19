import React, {Fragment, useState} from 'react';
import {Route, Routes, useNavigate} from 'react-router-dom';
import LoginPage from './components/LoginPage.tsx';
import {Button} from "@mantine/core";
import {useQueryClient} from "@tanstack/react-query";

function App() {
    const navigate = useNavigate()

    const goToLoginPage = () => {
        navigate("/login")
    }

    const goToHomePage = () => {
        navigate("/")
    }


    return (
        <Fragment>

            <Button onClick={() => goToLoginPage()}> Login Button</Button>
            <Button onClick={() => goToHomePage()}> Home page</Button>
            <Routes>
                <Route path="/login" element={<LoginPage />}></Route>
            </Routes>
        </Fragment>
    );
}

export default App;
