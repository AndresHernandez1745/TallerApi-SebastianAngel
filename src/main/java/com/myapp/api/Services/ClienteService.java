package com.myapp.api.Services;

import com.myapp.api.DTO.ClienteDTO;
import com.myapp.api.Mapper.ClienteMapper;
import com.myapp.api.Repository.ClienteRepository;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClienteService {
    
   @Autowired
   private ClienteRepository clienteRepository;
   @Autowired
   private ClienteMapper clienteMapper;

   public ClienteService() {
   }

   public Iterable<ClienteDTO> findAll() {
      return this.clienteRepository.findAll()
         .stream()
         .map(clienteMapper::toDto)
         .collect(Collectors.toList());
   }

   public Optional<ClienteDTO> findById(Long id) {
      return this.clienteRepository.findById(id)
         .map(clienteMapper::toDto);
   }

   public ClienteDTO save(ClienteDTO clienteDTO) {
      return this.clienteMapper.toDto(
         this.clienteRepository.save(
            this.clienteMapper.toEntity(clienteDTO)
         )
      );
   }

   public ClienteDTO update(ClienteDTO clienteDTO) {
      return this.clienteMapper.toDto(
         this.clienteRepository.save(
            this.clienteMapper.toEntity(clienteDTO)
         )
      );
   }

   public void delete(Long id) {
      this.clienteRepository.deleteById(id);
   }

   public boolean existsById(Long id) {
      return this.clienteRepository.existsById(id);
   }

   public long count() {
      return this.clienteRepository.count();
   }
}
