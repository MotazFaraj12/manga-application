import React, { useEffect, useState } from 'react'
import {listMangas} from '../service/mangaService'
function Home() {
  const [mangas , setMangas] = useState([])
  
  useEffect(() =>{
    listMangas().then((response) => {
      setMangas(response.data);
    }).catch(error => {
      console.error(error);
    })
  } , [])

  return (
    <div>Home</div>
  )
}

export default Home