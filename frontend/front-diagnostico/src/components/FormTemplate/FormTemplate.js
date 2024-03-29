import { Button, Input, Stack } from '@mui/joy';
import axios from 'axios';
import React, { useState } from 'react';


function FormTemplate(props) {
  const [name, setName] = useState('');
  const [phone, setPhone] = useState('');
  const [topic, setTopic] = useState('');
  const [date, setDate] = useState('');


  if (props.type === 'users') {
    return (
      <form action={props.action} onSubmit={(event) => {
        event.preventDefault();
        axios.post('http://localhost:8080'+props.action, {
          name: name,
          phone: phone
        })
          .then(function (response) {
            console.log(response);
            alert('Usuario agregado')
          })
          .catch(function (error) {
            console.log(error);
          });
      }}>
        <Stack spacing={1}>
          <Input placeholder='Nombre' required onChange={(event) => {
            setName(event.currentTarget.value)
          }}></Input>
          <Input placeholder='Numero de telefono' required onChange={(event) => {
            setPhone(event.currentTarget.value)
          }}></Input>
          <Button type='Submit'>Enviar</Button>
        </Stack>
      </form>
    )
  }

  if (props.type === 'sessions') {
    return (
      <form action={props.action} onSubmit={(event) => {
        event.preventDefault();
        axios.post('http://localhost:8080'+props.action, {
          name: name,
          topic: topic,
          date: date
        })
          .then(function (response) {
            console.log(response);
            alert('Sesion creada')
            window.location.reload();
          })
          .catch(function (error) {
            console.log(error);
          });
      }}>
        <Stack spacing={1}>
          <Input placeholder='Nombre' required onChange={(event) => {
            setName(event.currentTarget.value)
          }}></Input>
          <Input placeholder='Tema de la sesion' required onChange={(event) => {
            setTopic(event.currentTarget.value)
          }}></Input>
          <Input placeholder='Fecha de la sesion' type='date' required onChange={(event) => {
            setDate(event.currentTarget.value)
          }}></Input>
          <Button type='Submit'>Enviar</Button>
        </Stack>
      </form>
    )
  }

  if (props.type === 'sessionEdit') {

    let sessionToEdit = JSON.parse(sessionStorage.getItem('sessionToEdit'))

    return (
      <form action={props.action} onSubmit={(event) => {
        event.preventDefault();
        axios.put('http://localhost:8080'+props.action, {
          id_session: sessionToEdit.id_session,
          name: name || sessionToEdit.name,
          topic: topic || sessionToEdit.topic,
          date: date || sessionToEdit.date
        })
          .then(function (response) {
            console.log(response);
            alert('Sesion actualizada')
            sessionStorage.clear();
            window.location.reload();
          })
          .catch(function (error) {
            console.log(error);
          });
      }}>
        <Stack spacing={1}>
          <Input placeholder='Nombre' required 
          onChange={(event) => {
            setName(event.currentTarget.value)
          }} 
          defaultValue={sessionToEdit.name}
          ></Input>
          <Input placeholder='Tema de la sesion' required onChange={(event) => {
            setTopic(event.currentTarget.value)
          }} defaultValue={sessionToEdit.topic} ></Input>
          <Input placeholder='Fecha de la sesion' type='date' required onChange={(event) => {
            setDate(event.currentTarget.value)
          }} defaultValue={sessionToEdit.date.toString().slice(0,10)}></Input>
          <Button type='Submit'>Enviar</Button>
        </Stack>
      </form>
    )
  }

  if (props.type === 'searchUsers') {
    return (
      <form action={props.action} onSubmit={(event) => {
        event.preventDefault();
        axios.get('http://localhost:8080'+props.action+'?phone='+phone)
          .then(function (response) {
            if (response.data.datos[0].length !== 0) {
              for (const usuario of response.data.datos[0]) {
                if (window.confirm('Usuario encontrado: '+usuario.name+' desea agregarlo a la sesion?')) {
                  
                  axios.post('http://localhost:8080/api/sessions/enroll/id',
                    {
                      user: usuario.id_user,
                      session: props.id_session
                    }
                  ).then((response) => {
                    alert('Usuario agregado')
                    window.location.reload();
                  }).catch((error) => {
                    alert('ha ocurrido un error')
                    console.log(error);
                  })
                  
                }
              }
            }else{
              alert('Usuario no encontrado')
            }
          })
          .catch(function (error) {
            console.log(error);
            alert('Ha ocurrido un error')
          });
      }}>
        <Stack spacing={1}>
          <Input placeholder='Numero de telefono' required onChange={(event) => {
            setPhone(event.currentTarget.value)
          }}></Input>
          <Button type='Submit'>Enviar</Button>
          
        </Stack>
      </form>
    )
  }
};

export default FormTemplate;
