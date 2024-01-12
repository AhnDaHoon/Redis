<script lang="ts">
	import type { Item } from '$services/types';
	import Card from '$lib/components/card.svelte';
	import Carousel from '$lib/components/carousel.svelte';

	export let endingSoonest: Item[] = [];
	export let mostViews: Item[] = [];
	export let highestPrice: Item[] = [];
</script>

<h1 class="text-3xl">가장 비싼 아이템</h1>
<Carousel>
	{#each highestPrice as item}
		<div class="flex-1">
			<Card {item} />
		</div>
	{/each}
</Carousel>

<hr class="my-8" />

<h1 class="text-3xl">곧 마감되는 아이템</h1>
<Carousel>
	{#each endingSoonest as item}
		<div class="flex-1">
			<Card {item} />
		</div>
	{/each}
</Carousel>

<hr class="my-8" />

<h1 class="text-3xl">인기 아이템</h1>
<Carousel>
	{#each mostViews as item}
		<div class="flex-1">
			<Card {item} showViews />
		</div>
	{/each}
</Carousel>
