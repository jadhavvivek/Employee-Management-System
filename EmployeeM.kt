import java.util.Scanner
import Employee
class EmployeeManagementSystem {
    private val employees = Array<Employee?>(100) { null }
    private var employeeCount = 0

    fun run() {
        val scanner = Scanner(System.`in`)

        while (true) {
            println("Employee Management System")
            println("1. Read all employees")
            println("2. Add employee")
            println("3. Update employee")
            println("4. Delete employee")
            println("5. Exit")
            print("Enter your choice: ")

            when (scanner.nextInt()) {
                1 -> readAllEmployees()
                2 -> addEmployee(scanner)
                3 -> updateEmployee(scanner)
                4 -> deleteEmployee(scanner)
                5 -> {
                    println("Exiting...")
                    return
                }
                else -> println("Invalid choice, please try again.")
            }
        }
    }

    private fun readAllEmployees() {
        if (employeeCount == 0) {
            println("No employees found.")
        } else {
            for (i in 0 until employeeCount) {
                println(employees[i])
            }
        }
    }

    private fun addEmployee(scanner: Scanner) {
        if (employeeCount >= employees.size) {
            println("Employee list is full.")
            return
        }
        print("Enter employee ID: ")
        val id = scanner.nextInt()
        scanner.nextLine() // Consume newline
        print("Enter employee name: ")
        val name = scanner.nextLine()
        print("Enter employee position: ")
        val position = scanner.nextLine()
        employees[employeeCount] = Employee(id, name, position)
        employeeCount++
        println("Employee added successfully.")
    }

    private fun updateEmployee(scanner: Scanner) {
        print("Enter employee ID to update: ")
        val id = scanner.nextInt()
        scanner.nextLine() // Consume newline
        val employee = findEmployeeById(id)
        if (employee != null) {
            print("Enter new name (current: ${employee.name}): ")
            val newName = scanner.nextLine()
            print("Enter new position (current: ${employee.position}): ")
            val newPosition = scanner.nextLine()
            employee.name = newName
            employee.position = newPosition
            println("Employee updated successfully.")
        } else {
            println("Employee not found.")
        }
    }

    private fun deleteEmployee(scanner: Scanner) {
        print("Enter employee ID to delete: ")
        val id = scanner.nextInt()
        scanner.nextLine() // Consume newline
        for (i in 0 until employeeCount) {
            if (employees[i]?.id == id) {
                employees[i] = employees[employeeCount - 1]
                employees[employeeCount - 1] = null
                employeeCount--
                println("Employee deleted successfully.")
                return
            }
        }
        println("Employee not found.")
    }

    private fun findEmployeeById(id: Int): Employee? {
        for (i in 0 until employeeCount) {
            if (employees[i]?.id == id) {
                return employees[i]
            }
        }
        return null
    }
}

fun main() {
    val system = EmployeeManagementSystem()
    system.run()
}
