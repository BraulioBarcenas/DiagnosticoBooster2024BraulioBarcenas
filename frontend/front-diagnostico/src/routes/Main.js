import { Box, Stack } from '@mui/joy';
import React from 'react';
import AccordionAPI from '../components/Accordion/AccordionAPI';
import FormTemplate from '../components/FormTemplate/FormTemplate';

function Main() {

    let sessionType = '';
    let sessionFormHeader= '';
    let sessionAction= '';
    
    if (sessionStorage.getItem('sessionToEdit')) {
        sessionFormHeader='Edita la sesion'
        sessionType='sessionEdit'
        sessionAction='/api/sessions/update'
    } else {
        sessionFormHeader= 'Registrar Sesion'
        sessionType='sessions'
        sessionAction='/api/sessions/save'
    }

    return (
        <Stack direction={'row'}>
            <Box
                marginLeft={'3%'}
                width={'20%'}
            >            
                <h1>{sessionFormHeader}</h1>
                <FormTemplate action={sessionAction} type={sessionType}></FormTemplate>
                <h1>Registrar usuario</h1>
                <FormTemplate action='/api/users/save' type='users'></FormTemplate>
            </Box>
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                minHeight="100vh"
                width="80%"
                margin={"auto"}
                flexDirection={"column"}
            >

                <h1>Lista de sesiones</h1>
                <AccordionAPI></AccordionAPI>
            </Box>
        </Stack>
    );
}


export default Main;