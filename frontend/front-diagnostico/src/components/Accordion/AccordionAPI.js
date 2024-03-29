import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Accordion from '@mui/joy/Accordion';
import AccordionDetails from '@mui/joy/AccordionDetails';
import AccordionGroup from '@mui/joy/AccordionGroup';
import AccordionSummary from '@mui/joy/AccordionSummary';
import { ButtonGroup,Button, Stack } from '@mui/joy';
import FormTemplate from '../FormTemplate/FormTemplate';


function AccordionAPI() {
  const [sessions, setSessions] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/sessions')
      .then(response => {
        setSessions(response.data.datos[0]);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  return (
    <>
    <AccordionGroup sx={{ width: 0.8 }}>
      {sessions.map(session => (
        <Accordion key={session.id_session}>
          <AccordionSummary color='primary'><b>{session.name}</b></AccordionSummary>
          <AccordionDetails sx={{fontStyle: 'italic', width: 1}} >
            <Stack spacing={1.5} direction={'row'} alignItems={'center'} justifyContent={'space-between'}>
                <p>Tema: {session.topic} - Fecha: {session.date.slice(0,10)}</p>
                <ButtonGroup>
                  <Button component="a" href={'/attendance/'+session.id_session} color='success' variant='soft'>Control de asistencia</Button> 
                  <Button onClick={() => {
                    const sessionToEdit = {
                      id_session: session.id_session,
                      name: session.name,
                      topic: session.topic,
                      date: session.date
                    }
                    sessionStorage.setItem('sessionToEdit',JSON.stringify(sessionToEdit));
                    alert('Registra los cambios en el formulario')
                    window.location.reload();
                  }} color='warning' variant='soft'>Editar sesion</Button> 
                  <Button 
                   onClick={async () => {
                      if (window.confirm('Seguro que quieres eliminar la sesion '+session.name)) {
                        axios.delete('http://localhost:8080/api/sessions/delete?id_session='+session.id_session)
                        .then((response) => {
                          alert('Sesion eliminada');
                          window.location.reload();
                        })
                        .catch((error) => {
                          alert('Ha ocurrido un error')
                          console.log(error);
                        })
                      }
                   }}
                   color='danger' variant='soft'>Eliminar sesion</Button> 
                </ButtonGroup>
                <FormTemplate></FormTemplate>
            </Stack>
          </AccordionDetails>
        </Accordion>
      ))}
 
    </AccordionGroup>
    </>
  );
}



export default AccordionAPI;
