package com.create.file.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.create.file.model.Documento;
import com.create.file.repository.DocumentoRepository;
import com.create.file.services.DocumentoService;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController {
	
	@Autowired
	DocumentoService documentoService;
	
	@RequestMapping(value = "/add",method = RequestMethod.POST, consumes = "multipart/form-data")
	public void addDocumento(@RequestParam("documento") MultipartFile[] multipartFiles) throws NoSuchAlgorithmException{
		
		documentoService.addDocumentos(multipartFiles);
	}
	
	@GetMapping(value = "/download/{id}")
	public HttpEntity<byte[]> downloadFile(@PathVariable("id") long id)throws IOException{
		
		Documento doc = documentoService.getFile(id);
		
		byte[] arquivo = doc.getData();
		
		HttpHeaders header = new HttpHeaders();
		
		header.add("Content-Disposition", "attachment;filename=\""+ doc.getNome() +"\"");
		
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, header);
		
		return entity;
	}
}
