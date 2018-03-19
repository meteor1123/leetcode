	/*
		http://blog.csdn.net/dm_vincent/article/details/7655764
	*/
		
	/*


		Algorithm 			Constructor 	Union 		Find
		Quick-Find      		N            N      	  1
		Quick-Union     		N  		Tree height  Tree height
		Weighted Quick-Union    N  			lgN         lgN
		Weighted Quick-Union 
		With Path Compression   N   Very near to 1 (amortized)  Very near to 1 (amortized)
	*/
	/*
		以下是Quick Find
		缺陷：下述代码的find方法十分高效，因为仅仅需要一次数组读取操作就能够找到该节点的组号，但是问题随之而来，对于需要添加新路径的情况，就涉及到对于组号的修改，
			 因为并不能确定哪些节点的组号需要被修改，因此就必须对整个数组进行遍历，找到需要修改的节点，逐一修改，这一下每次添加新路径带来的复杂度就是线性关系了，
			 如果要添加的新路径的数量是M，节点数量是N，那么最后的时间复杂度就是MN，显然是一个平方阶的复杂度，对于大规模的数据而言，平方阶的算法是存在问题的，
			 这种情况下，每次添加新路径就是“牵一发而动全身”，想要解决这个问题，关键就是要提高union方法的效率，让它不再需要遍历整个数组。


		考虑一下，为什么以上的解法会造成“牵一发而动全身”？因为每个节点所属的组号都是单独记录，各自为政的，没有将它们以更好的方式组织起来，当涉及到修改的时候，
		除了逐一通知、修改，别无他法。所以现在的问题就变成了，如何将节点以更好的方式组织起来，组织的方式有很多种，但是最直观的还是将组号相同的节点组织在一起，想想所学的数据结构，
		什么样子的数据结构能够将一些节点给组织起来？常见的就是链表，图，树，什么的了。但是哪种结构对于查找和修改的效率最高？毫无疑问是树，因此考虑如何将节点和组的关系以树的形式表现出来。
	 	如果不改变底层数据结构，即不改变使用数组的表示方法的话。可以采用parent-link的方式将节点组织起来，
	 	举例而言，id[p]的值就是p节点的父节点的序号，如果p是树根的话，id[p]的值就是p，因此最后经过若干次查找，一个节点总是能够找到它的根节点
	 	，即满足id[root] = root的节点也就是组的根节点了，然后就可以使用根节点的序号来表示组号。
	 	所以在处理一个pair的时候，将首先找到pair中每一个节点的组号(即它们所在树的根节点的序号)，如果属于不同的组的话，就将其中一个根节点的父节点设置为另外一个根节点，
	 	相当于将一颗独立的树编程另一颗独立的树的子树。直观的过程如下图所示。但是这个时候又引入了问题。

	*/
	public class UF {
		private int[] id; //access to component id (site indexed)
		private int count; // number of components
		public UF(int N) {
			// Initialize component id array.
			count = N;
			id = new int[N];
			for (int i = 0; i < N; i++)
				id[i] = i;
		}
		public int count(){ 
			return count; 
		}
		public boolean connected(int p, int q) { 
			return find(p) == find(q); 
		}
		public int find(int p) { 
			return id[p]; 
		}
		public void union(int p, int q) { 
			// 获得p和q的组号
			int pID = find(p);
			int qID = find(q);
			// 如果两个组号相等，直接返回
			if (pID == qID) 
				return;
			// 遍历一次，改变组号使他们属于一个组
			for (int i = 0; i < id.length; i++) //将p的ID 全改为q的ID
				if (id[i] == pID) 
					id[i] = qID;
			count--;
		}
	}

	//改进Union和Find方法的Quick Union,
	/*
		树这种数据结构容易出现极端情况，因为在建树的过程中，树的最终形态严重依赖于输入数据本身的性质，比如数据是否排序，是否随机分布等等。
		比如在输入数据是有序的情况下，构造的BST会退化成一个链表。在我们这个问题中，也是会出现的极端情况的，

		为了克服这个问题，BST可以演变成为红黑树或者AVL树等等。
		然而，在我们考虑的这个应用场景中，每对节点之间是不具备可比性的。因此需要想其它的办法。在没有什么思路的时候，
		多看看相应的代码可能会有一些启发，考虑一下Quick-Union算法中的union方法实现：

		下面面 id[pRoot] = qRoot 这行代码看上去似乎不太对劲。因为这也属于一种“硬编码”，这样实现是基于一个约定，
		即p所在的树总是会被作为q所在树的子树，从而实现两颗独立的树的融合。那么这样的约定是不是总是合理的呢？
		显然不是，比如p所在的树的规模比q所在的树的规模大的多时，p和q结合之后形成的树就是十分不和谐的一头轻一头重的”畸形树“了。
	*/
	private int find(int p) { 
		// 寻找p节点所在组的根节点，根节点具有性质id[root] = root
		while (p != id[p]) 
			p = id[p];
		return p;
	}
	public void union(int p, int q) { 
		// Give p and q the same root.
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) 
			return;
		id[pRoot] = qRoot;    // 将一颗树(即一个组)变成另外一课树(即一个组)的子树
		count--;
	}



	/*
		我们应该考虑树的大小，然后再来决定到底是调用：
		id[pRoot] = qRoot 或者是 id[qRoot] = pRoot
		即总是size小的树作为子树和size大的树进行合并。这样就能够尽量的保持整棵树的平衡。
	 
		所以现在的问题就变成了：树的大小该如何确定？
		我们回到最初的情形，即每个节点最一开始都是属于一个独立的组，通过下面的代码进行初始化

		在初始情况下，每个组的大小都是1，因为只含有一个节点，所以我们可以使用额外的一个数组来维护每个组的大小，对该数组的初始化也很直观：
	*/
	//改进的Weighted quick-union

	for (int i = 0; i < N; i++)
		size[i] = 1;    // 初始情况下，每个组的大小都是1

	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) 
			return;
		// 将小树作为大树的子树
		if (size[i] < size[j]) { 
			id[i] = j; 
			size[j] += size[i]; 
		} else { 
			id[j] = i; 
			size[i] += size[j]; 
		}
		count--;
	}


	/*
		可以发现，通过sz数组决定如何对两棵树进行合并之后，最后得到的树的高度大幅度减小了。这是十分有意义的，因为在Quick-Union算法中的任何操作，
		都不可避免的需要调用find方法，而该方法的执行效率依赖于树的高度。树的高度减小了，find方法的效率就增加了，从而也就增加了整个Quick-Union算法的效率。
	 	上图其实还可以给我们一些启示，即对于Quick-Union算法而言，节点组织的理想情况应该是一颗十分扁平的树，所有的孩子节点应该都在height为1的地方，
	 	即所有的孩子都直接连接到根节点。这样的组织结构能够保证find操作的最高效率。

	 	那么如何构造这种理想结构呢？
		在find方法的执行过程中，不是需要进行一个while循环找到根节点嘛？如果保存所有路过的中间节点到一个数组中，然后在while循环结束之后，将这些中间节点的父节点指向根节点，不就行了么？
		但是这个方法也有问题，因为find操作的频繁性，会造成频繁生成中间节点数组，相应的分配销毁的时间自然就上升了。
		那么有没有更好的方法呢？还是有的，即将节点的父节点指向该节点的爷爷节点，这一点很巧妙，十分方便且有效，相当于在寻找根节点的同时，对路径进行了压缩，使整个树结构扁平化。
		相应的实现如下，实际上只需要添加一行代码
	*/
	private int find(int p) {
		while (p != id[p]) {
			// 将p节点的父节点设置为它的爷爷节点
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}

