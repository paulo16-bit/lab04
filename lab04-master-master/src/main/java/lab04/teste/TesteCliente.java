package lab04;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.repository.ClienteRepository;

import java.util.List;

public class TesteCliente {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);

        // Adiciona um Cliente
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Pedro");
        cliente1.setCpf("12345678810");
        cliente1.setEmail("ppedro@gmail.com");
        cliente1.setTelefone("98912345679");
        clienteRepository.inserir(cliente1);

        // Adiciona outro Cliente
        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria");
        cliente2.setCpf("10987654321");
        cliente2.setEmail("maria@gmail.com");
        cliente2.setTelefone("97987654321");
        clienteRepository.inserir(cliente2);

        // Atualiza o Cliente Pedro
        Cliente clienteAtualizado = clienteRepository.buscaPorId(cliente1.getIdCliente());
        clienteAtualizado.setTelefone("98123456789");
        clienteRepository.atualizar(clienteAtualizado);

        // Lista todos os Clientes
        List<Cliente> clientes = clienteRepository.mostrarTodos();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getIdCliente() + " - " + cliente.getNome() + " - " + cliente.getEmail());
        }

        // Deleta o Cliente Maria
        clienteRepository.deletar(cliente2);

        // Lista todos os Clientes após a exclusão
        clientes = clienteRepository.mostrarTodos();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getIdCliente() + " - " + cliente.getNome() + " - " + cliente.getEmail());
        }
    }
}
