package com.libreria.service;



import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;



@Service
public class CloudinaryService {

	
	 private final Cloudinary cloudinary;

	 public CloudinaryService(Cloudinary cloudinary) {
		super();
		this.cloudinary = cloudinary;
	 }
	 
	 public String subirImagen(MultipartFile archivo) throws IOException{
		 
		 Map uploadResult = cloudinary.uploader().upload(archivo.getBytes(), ObjectUtils.asMap(
	                "folder", "catalogo_libros" // Carpeta organizativa en Cloudinary
	        ));

	        // Retorna la URL HTTPS pública de la imagen
	        return uploadResult.get("secure_url").toString();

		 
	 }
}
