import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
// Please import this of you want tailwindcss to work, if still does not work disable -> enable tailwind css in webstorm
import './index.css';
import {QueryClient, QueryClientProvider} from '@tanstack/react-query';
import {createTheme, MantineProvider} from '@mantine/core';
import {BrowserRouter} from 'react-router-dom';
// Include this for mantine to work
import '@mantine/core/styles.css';

const theme = createTheme({
    /** Put your mantine theme override here */
});

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <BrowserRouter>
            <MantineProvider theme={theme}>
                <QueryClientProvider client={queryClient}>
                    <App />
                </QueryClientProvider>
            </MantineProvider>
        </BrowserRouter>
    </React.StrictMode>
);
