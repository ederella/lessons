from task_1_linked_list.decision import LinkedList, Node


def sum_linked_lists(list1:LinkedList, list2:LinkedList) ->LinkedList:
    result_list = LinkedList()
    if list1.len()!=list2.len():
        return result_list

    first_node = Node(list1.head.value + list2.head.value)
    result_list.head = first_node
    result_list.tail = first_node

    node1 = list1.head.next
    node2 = list2.head.next

    while node1 is not None and node2 is not None:
        new_node = Node(node1.value + node2.value)
        result_list.insert(result_list.tail , new_node)
        result_list.tail = new_node
        node1 = node1.next
        node2 = node2.next

    return result_list


def main():
    list1 = LinkedList()
    list1.add_in_tail(Node(3))
    list1.add_in_tail(Node(2))
    list1.add_in_tail(Node(3))
    list1.add_in_tail(Node(4))
    list1.add_in_tail(Node(3))
    list1.add_in_tail(Node(3))

    list2 = LinkedList()
    list2.add_in_tail(Node(3))
    list2.add_in_tail(Node(2))
    list2.add_in_tail(Node(3))
    list2.add_in_tail(Node(4))
    list2.add_in_tail(Node(3))
    list2.add_in_tail(Node(3))

    summed_list = sum_linked_lists(list1,list2)

    summed_list.print_all_nodes()

main()
