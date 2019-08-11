package com.create.file.controller;

import java.security.NoSuchAlgorithmException;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.create.file.repository.DocumentoRepository;
import com.create.file.services.DocumentoService;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController {
	
	@Autowired
	DocumentoService documentoService;
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(DocumentoController.class);
	
	@RequestMapping(value = "/add",method = RequestMethod.POST, consumes = "multipart/form-data")
	public void addDocumento(@RequestParam("documento") MultipartFile[] multipartFiles) throws NoSuchAlgorithmException{
		LOG.debug("Adicionando documento >>>>>>>>");
		documentoService.addDocumentos(multipartFiles);
		LOG.debug("<<<<<< Documento adicoionado");
		
	}
}
